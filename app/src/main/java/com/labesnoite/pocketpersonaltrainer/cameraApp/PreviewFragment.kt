package com.labesnoite.pocketpersonaltrainer.cameraApp

import android.Manifest
import android.annotation.TargetApi
import android.content.Context
import android.hardware.camera2.*
import android.media.MediaRecorder
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.SystemClock
import android.support.v4.app.Fragment
import android.util.Log
import android.util.SparseIntArray
import android.view.*
import com.labesnoite.pocketpersonaltrainer.R
import kotlinx.android.synthetic.main.fragment_preview.*
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class PreviewFragment : Fragment() {

    private val MAX_PREVIEW_WIDTH = 1280
    private val MAX_PREVIEW_HEIGHT = 720
    private lateinit var captureSession: CameraCaptureSession
    private lateinit var captureRequestBuilder: CaptureRequest.Builder
    private var cameraFacing = CameraCharacteristics.LENS_FACING_BACK
    private var toggleCameraFlag = false
    private var isCaptured = false

    private lateinit var cameraDevice: CameraDevice
    private val deviceStateCallback = object : CameraDevice.StateCallback() {
        override fun onOpened(camera: CameraDevice) {
            Log.d(TAG, "camera device opened")
            cameraDevice = camera
            previewSession()
        }

        override fun onDisconnected(camera: CameraDevice) {
            Log.d(TAG, "camera device disconnected")
            camera.close()
        }

        override fun onError(camera: CameraDevice, error: Int) {
            Log.d(TAG, "camera device error")
            this@PreviewFragment.activity?.finish()
        }

        override fun onClosed(camera: CameraDevice) {
            super.onClosed(camera)
            // Camera is now closed. We can start another session if requested.
            if (toggleCameraFlag) {
                toggleCameraFlag = false
                connectCamera()
            }
        }
    }
    private lateinit var backgroundThread: HandlerThread
    private lateinit var backgroundHandler: Handler
    private val cameraManager by lazy {
        activity?.getSystemService(Context.CAMERA_SERVICE) as CameraManager
    }

    private lateinit var surfaceHolder: SurfaceHolder
    private val surfaceListener = object : SurfaceHolder.Callback {
        override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
            Log.d(TAG, "surface changed")
            if (holder != null) {
                closeCamera()
                surfaceHolder = holder
                surfaceHolder.setFixedSize(MAX_PREVIEW_WIDTH, MAX_PREVIEW_HEIGHT)
                connectCamera()
            }
        }

        override fun surfaceDestroyed(holder: SurfaceHolder?) {
            Log.d(TAG, "surface destoryed")
        }

        override fun surfaceCreated(holder: SurfaceHolder?) {
            Log.d(TAG, "surface created")
        }

    }
    private lateinit var currentVideoFilePath: String
    private val mediaRecorder by lazy {
        MediaRecorder()
    }
    private var isRecording = false

    private fun previewSession() {
        val surface = surfaceHolder.surface
        captureRequestBuilder = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW)
        captureRequestBuilder.addTarget(surface)
        cameraDevice.createCaptureSession(Arrays.asList(surface),
                object : CameraCaptureSession.StateCallback() {
                    override fun onConfigureFailed(session: CameraCaptureSession?) {
                        Log.e(TAG, "creating capture session failded!")
                    }

                    override fun onConfigured(session: CameraCaptureSession?) {
                        if (session != null) {
                            captureSession = session
                            captureRequestBuilder.set(CaptureRequest.CONTROL_AF_MODE, CaptureRequest.CONTROL_AF_MODE_CONTINUOUS_PICTURE)
                            captureSession.setRepeatingRequest(captureRequestBuilder.build(), null, null)
                        }
                    }

                    override fun onReady(session: CameraCaptureSession) {
                        super.onReady(session)
                        // This was called from the stop preview session request
                        // All session requests should now have completed and we can
                        // now close the cameraDevice
                        if (toggleCameraFlag) cameraDevice.close()
                    }
                }, null)
    }

    private fun closeCamera() {
        if (this::captureSession.isInitialized)
            captureSession.close()
        if (this::cameraDevice.isInitialized)
            cameraDevice.close()
    }

    private fun startBackgroundThread() {
        backgroundThread = HandlerThread("Camara2 Kotlin").also { it.start() }
        backgroundHandler = Handler(backgroundThread.looper)
    }

    private fun stopBackgroundThread() {
        backgroundThread.quitSafely()
        try {
            backgroundThread.join()
        } catch (e: InterruptedException) {
            Log.e(TAG, e.toString())
        }
    }

    private fun <T> cameraCharacteristics(cameraId: String, key: CameraCharacteristics.Key<T>): T {
        val characteristics = cameraManager.getCameraCharacteristics(cameraId)
        return when (key) {
            CameraCharacteristics.LENS_FACING -> characteristics.get(key) as T
            CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP -> characteristics.get(key) as T
            CameraCharacteristics.SENSOR_ORIENTATION -> characteristics.get(key) as T
            else -> throw  IllegalArgumentException("Key not recognized")
        }
    }

    private fun cameraId(lens: Int): String {
        var deviceId = listOf<String>()
        try {
            val cameraIdList = cameraManager.cameraIdList
            deviceId = cameraIdList.filter { lens == cameraCharacteristics(it, CameraCharacteristics.LENS_FACING) }
        } catch (e: CameraAccessException) {
            Log.e(TAG, e.toString())
        }
        return deviceId[0]
    }

    private fun connectCamera() {
        Log.d(TAG, "connecting camera")
        val deviceId = cameraId(cameraFacing)
        openCamera(deviceId)

    }

    companion object {
        const val REQUEST_CAMERA_PERMISSION = 100
        private val TAG = PreviewFragment::class.qualifiedName
        @JvmStatic
        fun newInstance() = PreviewFragment()

        private val SENSOR_DEFAULT_ORINTATION_DEGREES = 90
        private val SENSOR_INVERSE_ORINTATION_DEGREES = 270
        private val DEFAULT_ORIENTATION = SparseIntArray().apply {
            append(Surface.ROTATION_0, 90)
            append(Surface.ROTATION_90, 0)
            append(Surface.ROTATION_180, 270)
            append(Surface.ROTATION_270, 180)
        }
        private val INVERSE_ORIENTATION = SparseIntArray().apply {
            append(Surface.ROTATION_0, 270)
            append(Surface.ROTATION_90, 180)
            append(Surface.ROTATION_180, 90)
            append(Surface.ROTATION_270, 0)
        }
    }

    private fun setupMediaRecorder() {
        val rotation = activity?.windowManager?.defaultDisplay?.rotation
        val sensorOrientation = cameraCharacteristics(
                cameraId(cameraFacing),
                CameraCharacteristics.SENSOR_ORIENTATION
        )
        when (sensorOrientation) {
            SENSOR_DEFAULT_ORINTATION_DEGREES ->
                mediaRecorder.setOrientationHint(DEFAULT_ORIENTATION.get(rotation!!))
            SENSOR_INVERSE_ORINTATION_DEGREES ->
                mediaRecorder.setOrientationHint(INVERSE_ORIENTATION.get(rotation!!))
        }
        val file = createVideoFile()
        mediaRecorder.apply {
            setVideoEncoder(MediaRecorder.VideoSource.SURFACE)
            setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            setOutputFile(file.path)
            setVideoEncodingBitRate(10000000)
            setVideoFrameRate(30)
            setVideoSize(1920, 1080)
            setVideoEncoder(MediaRecorder.VideoEncoder.H264)
            prepare()
        }
    }

    private fun stopMediaRecorder() {
        mediaRecorder.apply {
            try {
                stop()
                reset()
            } catch (e: IllegalStateException) {
                Log.e(TAG, e.toString())
            }
        }
    }

    private fun recordSession() {

        setupMediaRecorder()

        val holderSurface = surfaceHolder.surface
        val recordSurface = mediaRecorder.surface

        captureRequestBuilder = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_RECORD)
        captureRequestBuilder.addTarget(holderSurface)
        captureRequestBuilder.addTarget(recordSurface)
        val surfaces = ArrayList<Surface>().apply {
            add(holderSurface)
            add(recordSurface)
        }
    }

    private fun startRecordSession() {
        startChronometer()
        recordSession()
    }

    private fun stopRecordSession() {
        stopChronometer()
        stopMediaRecorder()
        previewSession()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    @AfterPermissionGranted(REQUEST_CAMERA_PERMISSION)
    private fun checkCameraPermission() {
        if (EasyPermissions.hasPermissions(activity!!, Manifest.permission.CAMERA)) {
            Log.d(TAG, "App has camera permission")
            previewLayout.visibility = View.VISIBLE
            previewSurfaceView.holder.addCallback(surfaceListener)
        } else {
            EasyPermissions.requestPermissions(activity!!,
                    getString(R.string.request_permission),
                    REQUEST_CAMERA_PERMISSION,
                    Manifest.permission.CAMERA)
        }
    }

    @TargetApi(24)
    private fun startChronometer() {
        chronometer.base = SystemClock.elapsedRealtime()
        chronometer.setTextColor(resources.getColor(android.R.color.holo_red_dark, null))
        chronometer.start()
    }

    @TargetApi(24)
    private fun stopChronometer() {
        chronometer.setTextColor(resources.getColor(android.R.color.white, null))
        chronometer.stop()
    }

    override fun onResume() {
        super.onResume()
        startBackgroundThread()
        checkCameraPermission()
    }

    override fun onPause() {
        closeCamera()
        stopBackgroundThread()
        previewLayout.visibility = View.GONE
        previewSurfaceView.holder.removeCallback(surfaceListener)
        super.onPause()
    }

    private fun openCamera(deviceId: String) {
        checkCameraPermission()
        try {
            cameraManager.openCamera(deviceId, deviceStateCallback, backgroundHandler)
        } catch (e: CameraAccessException) {
            Log.e(TAG, e.toString())
        } catch (e: InterruptedException) {
            throw RuntimeException("Interrupted while trying to lock the camera for opening")
        }

    }

    private fun createVideoFileName(): String {
        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        return "VIDEO_${timestamp}.mp4"
    }

    private fun createVideoFile(): File {
        val videoFile = File(context?.filesDir, createVideoFileName())
        currentVideoFilePath = videoFile.absolutePath
        return videoFile
    }

    fun toggleCameraFacing(): Int {
        toggleCameraFlag = true
        cameraFacing = if (cameraFacing == CameraCharacteristics.LENS_FACING_BACK)
            CameraCharacteristics.LENS_FACING_FRONT
        else
            CameraCharacteristics.LENS_FACING_BACK
        captureSession.stopRepeating()
        return cameraFacing
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        captureButton.setOnClickListener {
            if (isCaptured) {
                try {
                    isCaptured = false
                    previewSession()
                } catch (e: CameraAccessException) {
                }
            } else {
                isCaptured = true
                closeCamera()
            }
        }

        captureButton.setOnLongClickListener {
            if (isRecording) {
                isRecording = false
                stopRecordSession()
            } else {
                isRecording = true
                startRecordSession()
            }
            return@setOnLongClickListener isRecording
        }

        thumbnailButton.setOnClickListener {
            Log.d(TAG, "thumbnail button selected")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_preview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN or
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
    }
}