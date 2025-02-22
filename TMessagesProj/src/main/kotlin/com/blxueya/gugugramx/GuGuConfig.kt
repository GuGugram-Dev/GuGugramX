package com.blxueya.gugugramx

import android.content.Context
import android.content.SharedPreferences
import android.util.Base64
import org.telegram.messenger.ApplicationLoader
import tw.nekomimi.nekogram.config.ConfigItem
import java.io.ByteArrayInputStream
import java.io.ObjectInputStream


object GuGuConfig {
    private const val TAG = "GuGugrammX"

    val preferences: SharedPreferences = ApplicationLoader.applicationContext.getSharedPreferences("nkmrcfg", Context.MODE_PRIVATE)

    val sync = Any()

    private var configLoaded = false

    private val configs = ArrayList<ConfigItem>()

    // Text Style
    var showTextBold = addConfig("TextBold", ConfigItem.configTypeBool, true)

    var showTextItalic = addConfig("TextItalic", ConfigItem.configTypeBool, true)

    var showTextMono = addConfig("TextMonospace", ConfigItem.configTypeBool, true)

    var showTextStrikethrough = addConfig("TextStrikethrough", ConfigItem.configTypeBool, true)

    var showTextUnderline = addConfig("TextUnderline", ConfigItem.configTypeBool, true)

    var showTextSpoiler = addConfig("TextSpoiler", ConfigItem.configTypeBool, true)

    var showTextCreateLink = addConfig("TextLink", ConfigItem.configTypeBool, true)

    var showTextCreateMention = addConfig("TextCreateMention", ConfigItem.configTypeBool, true)

    var showTextRegular = addConfig("TextRegular", ConfigItem.configTypeBool, true)

    val showTextUndoRedo = addConfig("TextUndoRedo", ConfigItem.configTypeBool, false)

    // Configs
    var invertedNotification = addConfig("invertedNotification",ConfigItem.configTypeBool,false)

    var ForceAllowCopy = addConfig("ForceAllowCopy", ConfigItem.configTypeBool, false)

    var AlwaysSaveChatOffset = addConfig("AlwaysSaveChatOffset",ConfigItem.configTypeBool,false)

    var showRepeatAsCopy = addConfig("RepeatAsCopy",ConfigItem.configTypeBool,false)

    var showInvertReply = addConfig("InvertReply",ConfigItem.configTypeBool,false)

    var doubleTapAction = addConfig("DoubleTapAction",ConfigItem.configTypeInt,0)

    var showForwarderName = addConfig("ShowForwarderName",ConfigItem.configTypeBool,false)

    var noiseSuppressAndVoiceEnhance = addConfig("NoiseSuppressAndVoiceEnhance",ConfigItem.configTypeBool,false)


    val codeSyntaxHighlight = addConfig("codeSyntaxHighlight",ConfigItem.configTypeBool,true)

    val keepCopyFormatting = addConfig("keepCopyFormatting",ConfigItem.configTypeBool,true)

    val showPremiumStarInChat = addConfig("ShowPremiumStarInChat", ConfigItem.configTypeBool, true)

    fun addConfig(
        k: String,
        t: Int,
        d: Any?
    ): ConfigItem {
        val a =
            ConfigItem(
                k,
                t,
                d
            )
        configs.add(
            a
        )
        return a
    }

    fun loadConfig(
        force: Boolean
    ) {
        synchronized(
            sync
        ) {
            if (configLoaded && !force) {
                return
            }
            for (i in configs.indices) {
                val o =
                    configs[i]
                if (o.type == ConfigItem.configTypeBool) {
                    o.value =
                        preferences.getBoolean(
                            o.key,
                            o.defaultValue as Boolean
                        )
                }
                if (o.type == ConfigItem.configTypeInt) {
                    o.value =
                        preferences.getInt(
                            o.key,
                            o.defaultValue as Int
                        )
                }
                if (o.type == ConfigItem.configTypeLong) {
                    o.value =
                        preferences.getLong(
                            o.key,
                            (o.defaultValue as Long)
                        )
                }
                if (o.type == ConfigItem.configTypeFloat) {
                    o.value =
                        preferences.getFloat(
                            o.key,
                            (o.defaultValue as Float)
                        )
                }
                if (o.type == ConfigItem.configTypeString) {
                    o.value =
                        preferences.getString(
                            o.key,
                            o.defaultValue as String
                        )
                }
                if (o.type == ConfigItem.configTypeSetInt) {
                    val ss =
                        preferences.getStringSet(
                            o.key,
                            HashSet()
                        )
                    val si =
                        HashSet<Int>()
                    for (s in ss!!) {
                        si.add(
                            s.toInt()
                        )
                    }
                    o.value =
                        si
                }
                if (o.type == ConfigItem.configTypeMapIntInt) {
                    val cv =
                        preferences.getString(
                            o.key,
                            ""
                        )
                    // Log.e("NC", String.format("Getting pref %s val %s", o.key, cv));
                    if (cv!!.isEmpty()) {
                        o.value =
                            HashMap<Int, Int>()
                    } else {
                        try {
                            val data =
                                Base64.decode(
                                    cv,
                                    Base64.DEFAULT
                                )
                            val ois =
                                ObjectInputStream(
                                    ByteArrayInputStream(
                                        data
                                    )
                                )
                            o.value =
                                ois.readObject() as HashMap<Int?, Int?>
                            if (o.value == null) {
                                o.value =
                                    HashMap<Int, Int>()
                            }
                            ois.close()
                        } catch (e: Exception) {
                            o.value =
                                HashMap<Int, Int>()
                        }
                    }
                }
            }
            configLoaded =
                true
        }
    }

    init {
        loadConfig(
            false
        )
    }
}
