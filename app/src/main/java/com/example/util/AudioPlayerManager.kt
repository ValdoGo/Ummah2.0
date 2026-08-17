package com.example.util

import android.app.DownloadManager
import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.os.Environment
import android.widget.Toast
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

enum class PlaybackState {
    IDLE,
    BUFFERING,
    PLAYING,
    PAUSED,
    ERROR
}

data class AudioTrackInfo(
    val url: String,
    val title: String,
    val subtitle: String = "",
    val id: String = ""
)

class AudioPlayerManager(private val context: Context) {

    private var mediaPlayer: MediaPlayer? = null

    private val _playbackState = MutableStateFlow(PlaybackState.IDLE)
    val playbackState: StateFlow<PlaybackState> = _playbackState.asStateFlow()

    private val _currentTrack = MutableStateFlow<AudioTrackInfo?>(null)
    val currentTrack: StateFlow<AudioTrackInfo?> = _currentTrack.asStateFlow()

    fun play(track: AudioTrackInfo) {
        if (_currentTrack.value?.url == track.url && mediaPlayer != null) {
            if (mediaPlayer?.isPlaying == true) {
                mediaPlayer?.pause()
                _playbackState.value = PlaybackState.PAUSED
                return
            } else if (_playbackState.value == PlaybackState.PAUSED) {
                mediaPlayer?.start()
                _playbackState.value = PlaybackState.PLAYING
                return
            }
        }

        stop()
        _currentTrack.value = track
        _playbackState.value = PlaybackState.BUFFERING

        try {
            mediaPlayer = MediaPlayer().apply {
                setAudioAttributes(
                    AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
                )
                setDataSource(track.url)
                setOnPreparedListener { mp ->
                    mp.start()
                    _playbackState.value = PlaybackState.PLAYING
                }
                setOnCompletionListener {
                    _playbackState.value = PlaybackState.IDLE
                }
                setOnErrorListener { _, _, _ ->
                    _playbackState.value = PlaybackState.ERROR
                    Toast.makeText(context, "Erro ao reproduzir áudio", Toast.LENGTH_SHORT).show()
                    true
                }
                prepareAsync()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            _playbackState.value = PlaybackState.ERROR
            Toast.makeText(context, "Não foi possível carregar o áudio", Toast.LENGTH_SHORT).show()
        }
    }

    fun pause() {
        try {
            if (mediaPlayer?.isPlaying == true) {
                mediaPlayer?.pause()
                _playbackState.value = PlaybackState.PAUSED
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun resume() {
        try {
            if (mediaPlayer != null && _playbackState.value == PlaybackState.PAUSED) {
                mediaPlayer?.start()
                _playbackState.value = PlaybackState.PLAYING
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun stop() {
        try {
            mediaPlayer?.stop()
            mediaPlayer?.reset()
            mediaPlayer?.release()
            mediaPlayer = null
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            _playbackState.value = PlaybackState.IDLE
        }
    }

    fun downloadAudio(url: String, fileName: String, title: String) {
        try {
            val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as? DownloadManager
            if (downloadManager == null) {
                Toast.makeText(context, "Gerenciador de download não disponível", Toast.LENGTH_SHORT).show()
                return
            }

            val cleanFileName = fileName.replace(Regex("[^a-zA-Z0-9_.-]"), "_") + ".mp3"
            val request = DownloadManager.Request(Uri.parse(url)).apply {
                setTitle(title)
                setDescription("Baixando recitação do Alcorão")
                setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, cleanFileName)
                setAllowedOverMetered(true)
                setAllowedOverRoaming(true)
            }
            downloadManager.enqueue(request)
            Toast.makeText(context, "Download iniciado: $title", Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "Não foi possível iniciar o download: ${e.localizedMessage}", Toast.LENGTH_SHORT).show()
        }
    }
}
