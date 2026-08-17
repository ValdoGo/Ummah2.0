package com.example.data.repository

import com.example.data.model.AyahAudioInfo
import com.example.data.model.AyahItem
import com.example.data.model.MutashabihVerseItem
import com.example.data.model.ReciterInfo
import com.example.data.model.SimilarVerseItem
import com.example.data.model.SurahAudioMeta
import com.example.data.model.SurahMeta
import com.example.data.model.SurahReciterAudio

object QuranDataStore {

    val popularReciters = listOf(
        ReciterInfo(1, "Mishary Rashid Alafasy", "مشاري راشد العفاسي", "Murattal"),
        ReciterInfo(2, "Abdul Rahman Al-Sudais", "عبدالرحمن السديس", "Murattal"),
        ReciterInfo(3, "Abdul Basit Abdul Samad", "عبدالباسط عبدالصمد", "Murattal"),
        ReciterInfo(4, "Abdul Basit (Mujawwad)", "عبدالباسط عبدالصمد - مجود", "Mujawwad"),
        ReciterInfo(5, "Maher Al Muaiqly", "ماهر المعيقلي", "Murattal"),
        ReciterInfo(6, "Saad Al-Ghamdi", "سعد الغامدي", "Murattal"),
        ReciterInfo(7, "Hani Ar-Rifai", "هاني الرفاعي", "Murattal"),
        ReciterInfo(8, "Abu Bakr Al Shatri", "أبوبكر الشاطري", "Murattal"),
        ReciterInfo(9, "Yasser Al-Dosari", "ياسر الدوسري", "Murattal"),
        ReciterInfo(10, "Saud Al-Shuraim", "سعود الشريم", "Murattal"),
        ReciterInfo(11, "Abdullah Al-Juhany", "عبدالله الجهني", "Murattal")
    )

    val surahsList: List<SurahMeta> = listOf(
        SurahMeta(1, "الفاتحة", "Al-Fatihah", "Al-Fātiĥah", "The Opener", "makkah", 5, false, 7, listOf(1, 1), SurahAudioMeta(13, "https://download.quranicaudio.com/quran/mishaari_raashid_al_3afaasee/001.mp3")),
        SurahMeta(2, "البقرة", "Al-Baqarah", "Al-Baqarah", "The Cow", "madinah", 87, true, 286, listOf(2, 49), SurahAudioMeta(13, "https://download.quranicaudio.com/quran/mishaari_raashid_al_3afaasee/002.mp3")),
        SurahMeta(3, "آل عمران", "Ali 'Imran", "Āli `Imrān", "Family of Imran", "madinah", 89, true, 200, listOf(50, 76), SurahAudioMeta(13, "https://download.quranicaudio.com/quran/mishaari_raashid_al_3afaasee/003.mp3")),
        SurahMeta(4, "النساء", "An-Nisa", "An-Nisā", "The Women", "madinah", 92, true, 176, listOf(77, 106), SurahAudioMeta(13, "https://download.quranicaudio.com/quran/mishaari_raashid_al_3afaasee/004.mp3")),
        SurahMeta(5, "المائدة", "Al-Ma'idah", "Al-Mā'idah", "The Table Spread", "madinah", 112, true, 120, listOf(106, 127), SurahAudioMeta(13, "https://download.quranicaudio.com/quran/mishaari_raashid_al_3afaasee/005.mp3")),
        SurahMeta(6, "الأنعام", "Al-An'am", "Al-'An`ām", "The Cattle", "makkah", 55, true, 165, listOf(128, 150), SurahAudioMeta(13, "https://download.quranicaudio.com/quran/mishaari_raashid_al_3afaasee/006.mp3")),
        SurahMeta(7, "الأعراف", "Al-A'raf", "Al-'A`rāf", "The Heights", "makkah", 39, true, 206, listOf(151, 176), SurahAudioMeta(13, "https://download.quranicaudio.com/quran/mishaari_raashid_al_3afaasee/007.mp3")),
        SurahMeta(8, "الأنفال", "Al-Anfal", "Al-'Anfāl", "The Spoils of War", "madinah", 88, true, 75, listOf(177, 186), SurahAudioMeta(13, "https://download.quranicaudio.com/quran/mishaari_raashid_al_3afaasee/008.mp3")),
        SurahMeta(9, "التوبة", "At-Tawbah", "At-Tawbah", "The Repentance", "madinah", 113, false, 129, listOf(187, 207), SurahAudioMeta(13, "https://download.quranicaudio.com/quran/mishaari_raashid_al_3afaasee/009.mp3")),
        SurahMeta(10, "يونس", "Yunus", "Yūnus", "Jonah", "makkah", 51, true, 109, listOf(208, 221), SurahAudioMeta(13, "https://download.quranicaudio.com/quran/mishaari_raashid_al_3afaasee/010.mp3")),
        SurahMeta(11, "هود", "Hud", "Hūd", "Hud", "makkah", 52, true, 123, listOf(221, 235), SurahAudioMeta(13, "https://download.quranicaudio.com/quran/mishaari_raashid_al_3afaasee/011.mp3")),
        SurahMeta(12, "يوسف", "Yusuf", "Yūsuf", "Joseph", "makkah", 53, true, 111, listOf(235, 248), SurahAudioMeta(13, "https://download.quranicaudio.com/quran/mishaari_raashid_al_3afaasee/012.mp3")),
        SurahMeta(13, "الرعد", "Ar-Ra'd", "Ar-Ra`d", "The Thunder", "madinah", 96, true, 43, listOf(249, 255), SurahAudioMeta(13, "https://download.quranicaudio.com/quran/mishaari_raashid_al_3afaasee/013.mp3")),
        SurahMeta(14, "ابراهيم", "Ibrahim", "Ibrāhīm", "Abraham", "makkah", 72, true, 52, listOf(255, 261), SurahAudioMeta(13, "https://download.quranicaudio.com/quran/mishaari_raashid_al_3afaasee/014.mp3")),
        SurahMeta(15, "الحجر", "Al-Hijr", "Al-Ĥijr", "The Rocky Tract", "makkah", 54, true, 99, listOf(262, 267), SurahAudioMeta(13, "https://download.quranicaudio.com/quran/mishaari_raashid_al_3afaasee/015.mp3")),
        SurahMeta(16, "النحل", "An-Nahl", "An-Naĥl", "The Bee", "makkah", 70, true, 128, listOf(267, 281), SurahAudioMeta(13, "https://download.quranicaudio.com/quran/mishaari_raashid_al_3afaasee/016.mp3")),
        SurahMeta(17, "الإسراء", "Al-Isra", "Al-'Isrā'", "The Night Journey", "makkah", 50, true, 111, listOf(282, 293), SurahAudioMeta(13, "https://download.quranicaudio.com/quran/mishaari_raashid_al_3afaasee/017.mp3")),
        SurahMeta(18, "الكهف", "Al-Kahf", "Al-Kahf", "The Cave", "makkah", 69, true, 110, listOf(293, 304), SurahAudioMeta(13, "https://download.quranicaudio.com/quran/mishaari_raashid_al_3afaasee/018.mp3")),
        SurahMeta(19, "مريم", "Maryam", "Maryam", "Mary", "makkah", 44, true, 98, listOf(305, 312), SurahAudioMeta(13, "https://download.quranicaudio.com/quran/mishaari_raashid_al_3afaasee/019.mp3")),
        SurahMeta(20, "طه", "Taha", "Ṭāhā", "Ta-Ha", "makkah", 45, true, 135, listOf(312, 321), SurahAudioMeta(13, "https://download.quranicaudio.com/quran/mishaari_raashid_al_3afaasee/020.mp3")),
        SurahMeta(21, "الأنبياء", "Al-Anbiya", "Al-'Anbiyā'", "The Prophets", "makkah", 73, true, 112, listOf(322, 331), SurahAudioMeta(13, "https://download.quranicaudio.com/quran/mishaari_raashid_al_3afaasee/021.mp3")),
        SurahMeta(22, "الحج", "Al-Hajj", "Al-Ĥajj", "The Pilgrimage", "madinah", 103, true, 78, listOf(332, 341), SurahAudioMeta(13, "https://download.quranicaudio.com/quran/mishaari_raashid_al_3afaasee/022.mp3")),
        SurahMeta(23, "المؤمنون", "Al-Mu'minun", "Al-Mu'minūn", "The Believers", "makkah", 74, true, 118, listOf(342, 349), SurahAudioMeta(13, "https://download.quranicaudio.com/quran/mishaari_raashid_al_3afaasee/023.mp3")),
        SurahMeta(24, "النور", "An-Nur", "An-Nūr", "The Light", "madinah", 102, true, 64, listOf(350, 359), SurahAudioMeta(13, "https://download.quranicaudio.com/quran/mishaari_raashid_al_3afaasee/024.mp3")),
        SurahMeta(25, "الفرقان", "Al-Furqan", "Al-Furqān", "The Criterion", "makkah", 42, true, 77, listOf(359, 366), SurahAudioMeta(13, "https://download.quranicaudio.com/quran/mishaari_raashid_al_3afaasee/025.mp3")),
        SurahMeta(26, "الشعراء", "Ash-Shu'ara", "Ash-Shu`arā'", "The Poets", "makkah", 47, true, 227, listOf(367, 376), SurahAudioMeta(13, "https://download.quranicaudio.com/quran/mishaari_raashid_al_3afaasee/026.mp3")),
        SurahMeta(27, "النمل", "An-Naml", "An-Naml", "The Ant", "makkah", 48, true, 93, listOf(377, 385), SurahAudioMeta(13, "https://download.quranicaudio.com/quran/mishaari_raashid_al_3afaasee/027.mp3")),
        SurahMeta(28, "القصص", "Al-Qasas", "Al-Qaşaş", "The Stories", "makkah", 49, true, 88, listOf(385, 396), SurahAudioMeta(13, "https://download.quranicaudio.com/quran/mishaari_raashid_al_3afaasee/028.mp3")),
        SurahMeta(29, "العنكبوت", "Al-'Ankabut", "Al-`Ankabūt", "The Spider", "makkah", 85, true, 69, listOf(396, 404), SurahAudioMeta(13, "https://download.quranicaudio.com/quran/mishaari_raashid_al_3afaasee/029.mp3")),
        SurahMeta(30, "الروم", "Ar-Rum", "Ar-Rūm", "The Romans", "makkah", 84, true, 60, listOf(404, 410), SurahAudioMeta(13, "https://download.quranicaudio.com/quran/mishaari_raashid_al_3afaasee/030.mp3")),
        SurahMeta(31, "لقمان", "Luqman", "Luqmān", "Luqman", "makkah", 57, true, 34, listOf(411, 414), SurahAudioMeta(13, "https://download.quranicaudio.com/quran/mishaari_raashid_al_3afaasee/031.mp3")),
        SurahMeta(32, "السجدة", "As-Sajdah", "As-Sajdah", "The Prostration", "makkah", 75, true, 30, listOf(415, 417), SurahAudioMeta(13, "https://download.quranicaudio.com/quran/mishaari_raashid_al_3afaasee/032.mp3")),
        SurahMeta(33, "الأحزاب", "Al-Ahzab", "Al-'Aĥzāb", "The Combined Forces", "madinah", 90, true, 73, listOf(418, 427), SurahAudioMeta(13, "https://download.quranicaudio.com/quran/mishaari_raashid_al_3afaasee/033.mp3")),
        SurahMeta(34, "سبإ", "Saba", "Saba'", "Sheba", "makkah", 58, true, 54, listOf(428, 434), SurahAudioMeta(13, "https://download.quranicaudio.com/quran/mishaari_raashid_al_3afaasee/034.mp3")),
        SurahMeta(35, "فاطر", "Fatir", "Fāţir", "Originator", "makkah", 43, true, 45, listOf(434, 440), SurahAudioMeta(13, "https://download.quranicaudio.com/quran/mishaari_raashid_al_3afaasee/035.mp3")),
        SurahMeta(36, "يس", "Ya-Sin", "Yā-Sīn", "Ya Sin", "makkah", 41, true, 83, listOf(440, 445), SurahAudioMeta(13, "https://download.quranicaudio.com/quran/mishaari_raashid_al_3afaasee/036.mp3")),
        SurahMeta(37, "الصافات", "As-Saffat", "Aş-Şāffāt", "Those who set the Ranks", "makkah", 56, true, 182, listOf(446, 452), SurahAudioMeta(13, "https://download.quranicaudio.com/quran/mishaari_raashid_al_3afaasee/037.mp3")),
        SurahMeta(38, "ص", "Sad", "Şād", "The Letter \"Saad\"", "makkah", 38, true, 88, listOf(453, 458), SurahAudioMeta(13, "https://download.quranicaudio.com/quran/mishaari_raashid_al_3afaasee/038.mp3")),
        SurahMeta(39, "الزمر", "Az-Zumar", "Az-Zumar", "The Troops", "makkah", 59, true, 75, listOf(458, 467), SurahAudioMeta(13, "https://download.quranicaudio.com/quran/mishaari_raashid_al_3afaasee/039.mp3")),
        SurahMeta(40, "غافر", "Ghafir", "Ghāfir", "The Forgiver", "makkah", 60, true, 85, listOf(467, 476), SurahAudioMeta(13, "https://download.quranicaudio.com/quran/mishaari_raashid_al_3afaasee/040.mp3")),
        SurahMeta(41, "فصلت", "Fussilat", "Fuşşilat", "Explained in Detail", "makkah", 61, true, 54, listOf(477, 482), SurahAudioMeta(13, "https://download.quranicaudio.com/quran/mishaari_raashid_al_3afaasee/041.mp3")),
        SurahMeta(42, "الشورى", "Ash-Shuraa", "Ash-Shūrā", "The Consultation", "makkah", 62, true, 53, listOf(483, 489), SurahAudioMeta(13, "https://download.quranicaudio.com/quran/mishaari_raashid_al_3afaasee/042.mp3")),
        SurahMeta(43, "الزخرف", "Az-Zukhruf", "Az-Zukhruf", "The Ornaments of Gold", "makkah", 63, true, 89, listOf(489, 495), SurahAudioMeta(13, "https://download.quranicaudio.com/quran/mishaari_raashid_al_3afaasee/043.mp3")),
        SurahMeta(44, "الدخان", "Ad-Dukhan", "Ad-Dukhān", "The Smoke", "makkah", 64, true, 59, listOf(496, 498), SurahAudioMeta(13, "https://download.quranicaudio.com/quran/mishaari_raashid_al_3afaasee/044.mp3")),
        SurahMeta(45, "الجاثية", "Al-Jathiyah", "Al-Jāthiyah", "The Crouching", "makkah", 65, true, 37, listOf(499, 502), SurahAudioMeta(13, "https://download.quranicaudio.com/quran/mishaari_raashid_al_3afaasee/045.mp3")),
        SurahMeta(46, "الأحقاف", "Al-Ahqaf", "Al-'Aĥqāf", "The Wind-Curved Sandhills", "makkah", 66, true, 35, listOf(502, 506), SurahAudioMeta(13, "https://download.quranicaudio.com/quran/mishaari_raashid_al_3afaasee/046.mp3")),
        SurahMeta(47, "محمد", "Muhammad", "Muĥammad", "Muhammad", "madinah", 95, true, 38, listOf(507, 510), SurahAudioMeta(13, "https://download.quranicaudio.com/quran/mishaari_raashid_al_3afaasee/047.mp3")),
        SurahMeta(48, "الفتح", "Al-Fath", "Al-Fatĥ", "The Victory", "madinah", 111, true, 29, listOf(511, 515), SurahAudioMeta(13, "https://download.quranicaudio.com/quran/mishaari_raashid_al_3afaasee/048.mp3")),
        SurahMeta(49, "الحجرات", "Al-Hujurat", "Al-Ĥujurāt", "The Rooms", "madinah", 106, true, 18, listOf(515, 517), SurahAudioMeta(13, "https://download.quranicaudio.com/quran/mishaari_raashid_al_3afaasee/049.mp3")),
        SurahMeta(50, "ق", "Qaf", "Qāf", "The Letter \"Qaf\"", "makkah", 34, true, 45, listOf(518, 520), SurahAudioMeta(13, "https://download.quranicaudio.com/quran/mishaari_raashid_al_3afaasee/050.mp3")),
        SurahMeta(55, "الرحمن", "Ar-Rahman", "Ar-Raĥmān", "The Beneficent", "madinah", 97, true, 78, listOf(531, 534), SurahAudioMeta(13, "https://download.quranicaudio.com/quran/mishaari_raashid_al_3afaasee/055.mp3")),
        SurahMeta(56, "الواقعة", "Al-Waqi'ah", "Al-Wāqi`ah", "The Inevitable", "makkah", 46, true, 96, listOf(534, 537), SurahAudioMeta(13, "https://download.quranicaudio.com/quran/mishaari_raashid_al_3afaasee/056.mp3")),
        SurahMeta(67, "الملك", "Al-Mulk", "Al-Mulk", "The Sovereignty", "makkah", 77, true, 30, listOf(562, 564), SurahAudioMeta(13, "https://download.quranicaudio.com/quran/mishaari_raashid_al_3afaasee/067.mp3")),
        SurahMeta(78, "النبإ", "An-Naba", "An-Naba'", "The Tidings", "makkah", 80, true, 40, listOf(582, 583), SurahAudioMeta(13, "https://download.quranicaudio.com/quran/mishaari_raashid_al_3afaasee/078.mp3")),
        SurahMeta(112, "الإخلاص", "Al-Ikhlas", "Al-'Ikhlāş", "The Sincerity", "makkah", 22, true, 4, listOf(604, 604), SurahAudioMeta(13, "https://download.quranicaudio.com/quran/mishaari_raashid_al_3afaasee/112.mp3")),
        SurahMeta(113, "الفلق", "Al-Falaq", "Al-Falaq", "The Daybreak", "makkah", 20, true, 5, listOf(604, 604), SurahAudioMeta(13, "https://download.quranicaudio.com/quran/mishaari_raashid_al_3afaasee/113.mp3")),
        SurahMeta(114, "الناس", "An-Nas", "An-Nās", "Mankind", "makkah", 21, true, 6, listOf(604, 604), SurahAudioMeta(13, "https://download.quranicaudio.com/quran/mishaari_raashid_al_3afaasee/114.mp3"))
    )

    // Complete Surah 1 (Al-Fatihah) sample verses
    val alFatihahVerses = listOf(
        AyahItem("1:1", "Al-Fatihah", 1, "بِسْمِ ٱللَّهِ ٱلرَّحْمَـٰنِ ٱلرَّحِيمِ", "Bismi Allahi arrahmani arraheem", mapOf("sahih_international" to "In the name of Allāh, the Entirely Merciful, the Especially Merciful."), AyahAudioInfo("https://everyayah.com/data/Alafasy_128kbps/001001.mp3")),
        AyahItem("1:2", "Al-Fatihah", 2, "ٱلْحَمْدُ لِلَّهِ رَبِّ ٱلْعَـٰلَمِينَ", "Alhamdu lillahi rabbi alAAalameen", mapOf("sahih_international" to "[All] praise is [due] to Allāh, Lord of the worlds -"), AyahAudioInfo("https://everyayah.com/data/Alafasy_128kbps/001002.mp3")),
        AyahItem("1:3", "Al-Fatihah", 3, "ٱلرَّحْمَـٰنِ ٱلرَّحِيمِ", "Arrahmani arraheem", mapOf("sahih_international" to "The Entirely Merciful, the Especially Merciful,"), AyahAudioInfo("https://everyayah.com/data/Alafasy_128kbps/001003.mp3")),
        AyahItem("1:4", "Al-Fatihah", 4, "مَـٰلِكِ يَوْمِ ٱلدِّينِ", "Maliki yawmi addeen", mapOf("sahih_international" to "Sovereign of the Day of Recompense."), AyahAudioInfo("https://everyayah.com/data/Alafasy_128kbps/001004.mp3")),
        AyahItem("1:5", "Al-Fatihah", 5, "إِيَّاكَ نَعْبُدُ وَإِيَّاكَ نَسْتَعِينُ", "Iyyaka naAAbudu wa-iyyaka nastaAAeen", mapOf("sahih_international" to "It is You we worship and You we ask for help."), AyahAudioInfo("https://everyayah.com/data/Alafasy_128kbps/001005.mp3")),
        AyahItem("1:6", "Al-Fatihah", 6, "ٱهْدِنَا ٱلصِّرَٰطَ ٱلْمُسْتَقِيمَ", "Ihdina assirata almustaqeem", mapOf("sahih_international" to "Guide us to the straight path -"), AyahAudioInfo("https://everyayah.com/data/Alafasy_128kbps/001006.mp3")),
        AyahItem("1:7", "Al-Fatihah", 7, "صِرَٰطَ ٱلَّذِينَ أَنْعَمْتَ عَلَيْهِمْ غَيْرِ ٱلْمَغْضُوبِ عَلَيْهِمْ وَلَا ٱلضَّآلِّينَ", "Sirata allatheena anAAamta AAalayhim ghayri almaghdoobi AAalayhim wala addalleen", mapOf("sahih_international" to "The path of those upon whom You have bestowed favor, not of those who have earned [Your] anger or of those who are astray."), AyahAudioInfo("https://everyayah.com/data/Alafasy_128kbps/001007.mp3"))
    )

    // Complete Surah 112 (Al-Ikhlas) sample verses
    val alIkhlasVerses = listOf(
        AyahItem("112:1", "Al-Ikhlas", 1, "قُلْ هُوَ ٱللَّهُ أَحَدٌ", "Qul huwa Allahu ahad", mapOf("sahih_international" to "Say, \"He is Allāh, [who is] One,"), AyahAudioInfo("https://everyayah.com/data/Alafasy_128kbps/112001.mp3")),
        AyahItem("112:2", "Al-Ikhlas", 2, "ٱللَّهُ ٱلصَّمَدُ", "Allahu assamad", mapOf("sahih_international" to "Allāh, the Eternal Refuge."), AyahAudioInfo("https://everyayah.com/data/Alafasy_128kbps/112002.mp3")),
        AyahItem("112:3", "Al-Ikhlas", 3, "لَمْ يَلِدْ وَلَمْ يُولَدْ", "Lam yalid walam yoolad", mapOf("sahih_international" to "He neither begets nor is born,"), AyahAudioInfo("https://everyayah.com/data/Alafasy_128kbps/112003.mp3")),
        AyahItem("112:4", "Al-Ikhlas", 4, "وَلَمْ يَكُن لَّهُۥ كُفُوًا أَحَدٌۢ", "Walam yakun lahu kufuwan ahad", mapOf("sahih_international" to "Nor is there to Him any equivalent.\""), AyahAudioInfo("https://everyayah.com/data/Alafasy_128kbps/112004.mp3"))
    )

    // Sample Mutashabihat (Similar Verses)
    val sampleMutashabihat = listOf(
        MutashabihVerseItem(
            verse_key = "2:2",
            surah = 2,
            ayah = 2,
            surah_name_arabic = "البقرة",
            surah_name_english = "Al-Baqarah",
            arabic = "ذَٰلِكَ ٱلْكِتَـٰبُ لَا رَيْبَ ۛ فِيهِ ۛ هُدًى لِّلْمُتَّقِينَ",
            translation = "This is the Book about which there is no doubt, a guidance for those conscious of Allāh -",
            similar_verses = listOf(
                SimilarVerseItem("8:2", 8, 2, "الأنفال", "Al-Anfal", "إِنَّمَا ٱلْمُؤْمِنُونَ ٱلَّذِينَ إِذَا ذُكِرَ ٱللَّهُ وَجِلَتْ قُلُوبُهُمْ", "The believers are only those who, when Allāh is mentioned, their hearts become fearful..."),
                SimilarVerseItem("27:2", 27, 2, "النمل", "An-Naml", "هُدًى وَبُشْرَىٰ لِلْمُؤْمِنِينَ", "As guidance and good tidings for the believers"),
                SimilarVerseItem("31:3", 31, 3, "لقمان", "Luqman", "هُدًى وَرَحْمَةً لِّلْمُحْسِنِينَ", "As guidance and mercy for the doers of good")
            )
        ),
        MutashabihVerseItem(
            verse_key = "2:5",
            surah = 2,
            ayah = 5,
            surah_name_arabic = "البقرة",
            surah_name_english = "Al-Baqarah",
            arabic = "أُو۟لَـٰٓئِكَ عَلَىٰ هُدًى مِّن رَّبِّهِمْ ۖ وَأُو۟لَـٰٓئِكَ هُمُ ٱلْمُفْلِحُونَ",
            translation = "Those are upon [right] guidance from their Lord, and it is those who are the successful.",
            similar_verses = listOf(
                SimilarVerseItem("31:5", 31, 5, "لقمان", "Luqman", "أُو۟لَـٰٓئِكَ عَلَىٰ هُدًى مِّن رَّبِّهِمْ ۖ وَأُو۟لَـٰٓئِكَ هُمُ ٱلْمُفْلِحُونَ", "Those are upon [right] guidance from their Lord, and it is those who are the successful.")
            )
        )
    )
}
