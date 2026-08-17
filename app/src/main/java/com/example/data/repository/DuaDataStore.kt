package com.example.data.repository

import com.example.data.model.DuaCategory
import com.example.data.model.DuaItem

object DuaDataStore {

    val categories: List<DuaCategory> = listOf(
        DuaCategory("all", "Todas as Duas", "Todas as 126 súplicas autênticas", 126),
        DuaCategory("morning", "Morning Adhkar", "Supplications for the morning", 7),
        DuaCategory("evening", "Evening Adhkar", "Supplications for the evening", 5),
        DuaCategory("wudu", "Wudu & Purification", "Supplications for ablution and purification", 5),
        DuaCategory("prayer", "During Prayer", "Supplications said during salah", 8),
        DuaCategory("after_prayer", "After Prayer", "Dhikr and supplications after salah", 8),
        DuaCategory("sleep", "Sleep", "Before sleeping and upon waking", 6),
        DuaCategory("food", "Food & Drink", "Before and after eating", 6),
        DuaCategory("travel", "Travel", "Supplications for journeys", 6),
        DuaCategory("home", "Home", "Entering and leaving the home", 2),
        DuaCategory("masjid", "Masjid", "Entering and leaving the mosque", 4),
        DuaCategory("distress", "Distress & Anxiety", "Supplications during hardship", 7),
        DuaCategory("forgiveness", "Forgiveness", "Seeking forgiveness from Allah", 5),
        DuaCategory("illness", "Illness & Healing", "Supplications for the sick", 5),
        DuaCategory("weather", "Weather", "Rain, thunder, and wind", 3),
        DuaCategory("knowledge", "Knowledge", "Seeking beneficial knowledge", 3),
        DuaCategory("parents", "Parents", "Supplications for parents", 3),
        DuaCategory("guidance", "Guidance", "Seeking guidance and direction", 3),
        DuaCategory("gratitude", "Gratitude", "Thanking and praising Allah", 3),
        DuaCategory("protection", "Protection", "Seeking refuge and protection", 4),
        DuaCategory("dhikr", "Dhikr", "General remembrance of Allah", 6),
        DuaCategory("marriage", "Marriage & Family", "Supplications for marriage and family life", 4),
        DuaCategory("hajj", "Hajj & Umrah", "Supplications for pilgrimage", 4),
        DuaCategory("grief", "Grief & Loss", "Supplications at times of loss and death", 4),
        DuaCategory("children", "Children", "Supplications for children and newborns", 4),
        DuaCategory("business", "Business & Provision", "Supplications for livelihood and wealth", 4),
        DuaCategory("night_prayer", "Night Prayer", "Supplications for tahajjud, witr and the night", 4),
        DuaCategory("quran_recitation", "Quran Recitation", "Supplications before and during Quran recitation", 3)
    )

    val duas: List<DuaItem> = listOf(
        DuaItem(
            id = 1,
            category = "morning",
            title = "Morning Remembrance",
            arabic = "أَصْبَحْنَا وَأَصْبَحَ الْمُلْكُ لِلَّهِ وَالْحَمْدُ لِلَّهِ لَا إِلَهَ إِلَّا اللَّهُ وَحْدَهُ لَا شَرِيكَ لَهُ، لَهُ الْمُلْكُ وَلَهُ الْحَمْدُ وَهُوَ عَلَى كُلِّ شَيْءٍ قَدِيرٌ",
            transliteration = "Asbahna wa asbahal-mulku lillah, walhamdu lillah, la ilaha illallahu wahdahu la shareeka lah, lahul-mulku wa lahul-hamdu wa huwa 'ala kulli shay'in qadeer",
            translation = "Chegamos à manhã e, neste exato momento, todo o reino pertence a Allah. Todo louvor é para Allah. Não há divindade digna de adoração exceto Allah, o Único, sem parceiros. A Ele pertence a soberania e o louvor, e Ele é Onipotente sobre todas as coisas.",
            source = "Abu Dawud 4:317",
            repeat = 1
        ),
        DuaItem(
            id = 2,
            category = "morning",
            title = "Master of Forgiveness (Sayyid al-Istighfar)",
            arabic = "اللَّهُمَّ أَنْتَ رَبِّي لَا إِلَهَ إِلَّا أَنْتَ خَلَقْتَنِي وَأَنَا عَبْدُكَ وَأَنَا عَلَى عَهْدِكَ وَوَعْدِكَ مَا اسْتَطَعْتُ أَعُوذُ بِكَ مِنْ شَرِّ مَا صَنَعْتُ أَبُوءُ لَكَ بِنِعْمَتِكَ عَلَيَّ وَأَبُوءُ بِذَنْبِي فَاغْفِرْ لِي فَإِنَّهُ لَا يَغْفِرُ الذُّنُوبَ إِلَّا أَنْتَ",
            transliteration = "Allahumma anta rabbi la ilaha illa anta, khalaqtani wa ana 'abduka, wa ana 'ala 'ahdika wa wa'dika mastata'tu, a'udhu bika min sharri ma sana'tu, abu'u laka bini'matika 'alayya, wa abu'u bidhanbi, faghfir li, fa innahu la yaghfirudh-dhunuba illa anta",
            translation = "Ó Allah, Tu és meu Senhor, não há divindade digna de adoração exceto Tu. Tu me criaste e eu sou Teu servo, e cumpro Minha aliança e promessa contigo o melhor que posso. Busco refúgio em Ti do mal que cometi. Reconheço Tuas graças sobre mim e reconheço meus pecados; portanto, perdoa-me, pois ninguém perdoa os pecados exceto Tu.",
            source = "Sahih Al-Bukhari 7:150",
            repeat = 1
        ),
        DuaItem(
            id = 3,
            category = "morning",
            title = "Protection Morning Dua",
            arabic = "بِسْمِ اللَّهِ الَّذِي لَا يَضُرُّ مَعَ اسْمِهِ شَيْءٌ فِي الْأَرْضِ وَلَا فِي السَّمَاءِ وَهُوَ السَّمِيعُ الْعَلِيمُ",
            transliteration = "Bismillahilladhi la yadurru ma'asmihi shay'un fil-ardi wa la fis-sama'i wa huwas-sami'ul-'aleem",
            translation = "Em nome de Allah, com cujo nome nada na terra nem nos céus pode causar dano, e Ele é o Oniouvinte, o Onisciente.",
            source = "Abu Dawud 4:323, At-Tirmidhi 5:465",
            repeat = 3
        ),
        DuaItem(
            id = 4,
            category = "evening",
            title = "Evening Remembrance",
            arabic = "أَمْسَيْنَا وَأَمْسَى الْمُلْكُ لِلَّهِ وَالْحَمْدُ لِلَّهِ لَا إِلَهَ إِلَّا اللَّهُ وَحْدَهُ لَا شَرِيكَ لَهُ، لَهُ الْمُلْكُ وَلَهُ الْحَمْدُ وَهُوَ عَلَى كُلِّ شَيْءٍ قَدِيرٌ",
            transliteration = "Amsayna wa amsal-mulku lillah walhamdu lillah, la ilaha illallahu wahdahu la shareeka lah, lahul-mulku wa lahul-hamdu wa huwa 'ala kulli shay'in qadeer",
            translation = "Chegamos à noite e, neste exato momento, todo o reino pertence a Allah. Todo louvor é para Allah. Não há divindade exceto Allah, o Único, sem parceiros.",
            source = "Abu Dawud 4:317",
            repeat = 1
        ),
        DuaItem(
            id = 5,
            category = "evening",
            title = "Evening Protection",
            arabic = "أَعُوذُ بِكَلِمَاتِ اللَّهِ التَّامَّاتِ مِنْ شَرِّ مَا خَلَقَ",
            transliteration = "A'udhu bikalimatillahit-tammati min sharri ma khalaq",
            translation = "Busco refúgio nas palavras perfeitas de Allah contra o mal de tudo o que Ele criou.",
            source = "Sahih Muslim 4:2081",
            repeat = 3
        ),
        DuaItem(
            id = 6,
            category = "prayer",
            title = "Opening Supplication in Prayer (Du'a al-Istiftah)",
            arabic = "سُبْحَانَكَ اللَّهُمَّ وَبِحَمْدِكَ وَتَبَارَكَ اسْمُكَ وَتَعَالَى جَدُّكَ وَلَا إِلَهَ غَيْرُكَ",
            transliteration = "Subhanakallahumma wa bihamdika wa tabarakasmuka wa ta'ala jadduka wa la ilaha ghairuk",
            translation = "Glorificado sejas, ó Allah, e com Teu louvor; bendito é o Teu nome, exaltada é a Tua majestade e não há divindade além de Ti.",
            source = "Abu Dawud, At-Tirmidhi, An-Nasai",
            repeat = 1
        ),
        DuaItem(
            id = 7,
            category = "prayer",
            title = "Between Sujud (Prostrations)",
            arabic = "رَبِّ اغْفِرْ لِي رَبِّ اغْفِرْ لِي",
            transliteration = "Rabbighfir li, Rabbighfir li",
            translation = "Meu Senhor, perdoa-me. Meu Senhor, perdoa-me.",
            source = "Abu Dawud 1:231",
            repeat = 1
        ),
        DuaItem(
            id = 8,
            category = "prayer",
            title = "Dua in Sujud",
            arabic = "سُبْحَانَ رَبِّيَ الْأَعْلَى",
            transliteration = "Subhana Rabbiyal-A'la",
            translation = "Glorificado seja meu Senhor, o Altíssimo.",
            source = "Sahih Muslim",
            repeat = 3
        ),
        DuaItem(
            id = 9,
            category = "after_prayer",
            title = "Istighfar After Prayer",
            arabic = "أَسْتَغْفِرُ اللَّهَ",
            transliteration = "Astaghfirullah",
            translation = "Peço perdão a Allah.",
            source = "Sahih Muslim 1:414",
            repeat = 3
        ),
        DuaItem(
            id = 10,
            category = "after_prayer",
            title = "SubhanAllah After Prayer",
            arabic = "سُبْحَانَ اللَّهِ",
            transliteration = "SubhanAllah",
            translation = "Glória a Allah.",
            source = "Sahih Muslim 1:418",
            repeat = 33
        ),
        DuaItem(
            id = 11,
            category = "after_prayer",
            title = "Alhamdulillah After Prayer",
            arabic = "الْحَمْدُ لِلَّهِ",
            transliteration = "Alhamdulillah",
            translation = "Todo louvor pertence a Allah.",
            source = "Sahih Muslim 1:418",
            repeat = 33
        ),
        DuaItem(
            id = 12,
            category = "after_prayer",
            title = "Allahu Akbar After Prayer",
            arabic = "اللَّهُ أَكْبَرُ",
            transliteration = "Allahu Akbar",
            translation = "Allah é o Maior.",
            source = "Sahih Muslim 1:418",
            repeat = 33
        ),
        DuaItem(
            id = 13,
            category = "after_prayer",
            title = "Ayat al-Kursi After Prayer",
            arabic = "اللَّهُ لَا إِلَهَ إِلَّا هُوَ الْحَيُّ الْقَيُّومُ لَا تَأْخُذُهُ سِنَةٌ وَلَا نَوْمٌ لَّهُ مَا فِي السَّمَاوَاتِ وَمَا فِي الْأَرْضِ",
            transliteration = "Allahu la ilaha illa Huwal-Hayyul-Qayyum...",
            translation = "Allah! Não há divindade além d'Ele, o Vivente, o Sustentador de toda a existência... (Versículo do Trono, Al-Baqarah 2:255).",
            source = "An-Nasai, Al-Kubra",
            repeat = 1
        ),
        DuaItem(
            id = 14,
            category = "sleep",
            title = "Before Sleeping",
            arabic = "بِاسْمِكَ اللَّهُمَّ أَمُوتُ وَأَحْيَا",
            transliteration = "Bismika Allahumma amutu wa ahya",
            translation = "Em Teu nome, ó Allah, eu morro (durmo) e vivo (desperto).",
            source = "Sahih Al-Bukhari 11:113",
            repeat = 1
        ),
        DuaItem(
            id = 15,
            category = "sleep",
            title = "Upon Waking Up",
            arabic = "الْحَمْدُ لِلَّهِ الَّذِي أَحْيَانَا بَعْدَ مَا أَمَاتَنَا وَإِلَيْهِ النُّشُورُ",
            transliteration = "Alhamdu lillahilladhi ahyana ba'da ma amatana wa ilaihin-nushur",
            translation = "Louvado seja Allah, que nos deu vida após nos ter feito morrer, e a Ele será a ressurreição.",
            source = "Sahih Al-Bukhari 11:113",
            repeat = 1
        ),
        DuaItem(
            id = 16,
            category = "food",
            title = "Before Eating",
            arabic = "بِسْمِ اللَّهِ",
            transliteration = "Bismillah",
            translation = "Em nome de Allah.",
            source = "Abu Dawud 3:347, At-Tirmidhi 4:288",
            repeat = 1
        ),
        DuaItem(
            id = 17,
            category = "food",
            title = "After Eating",
            arabic = "الْحَمْدُ لِلَّهِ الَّذِي أَطْعَمَنِي هَذَا وَرَزَقَنِيهِ مِنْ غَيْرِ حَوْلٍ مِنِّي وَلَا قُوَّةٍ",
            transliteration = "Alhamdu lillahilladhi at'amani hadha wa razaqanihi min ghayri hawlin minni wa la quwwah",
            translation = "Louvado seja Allah, que me alimentou com isto e me proveu sem qualquer esforço ou força de minha parte.",
            source = "Abu Dawud, At-Tirmidhi, Ibn Majah",
            repeat = 1
        ),
        DuaItem(
            id = 18,
            category = "food",
            title = "Forgetting Bismillah Before Eating",
            arabic = "بِسْمِ اللَّهِ أَوَّلَهُ وَآخِرَهُ",
            transliteration = "Bismillahi awwalahu wa akhirah",
            translation = "Em nome de Allah em seu início e em seu fim.",
            source = "Abu Dawud 3:347, At-Tirmidhi 4:288",
            repeat = 1
        ),
        DuaItem(
            id = 19,
            category = "travel",
            title = "Travel Dua (Súplica da Viagem)",
            arabic = "سُبْحَانَ الَّذِي سَخَّرَ لَنَا هَذَا وَمَا كُنَّا لَهُ مُقْرِنِينَ وَإِنَّا إِلَى رَبِّنَا لَمُنْقَلِبُونَ",
            transliteration = "Subhanal-ladhi sakh-khara lana hadha wa ma kunna lahu muqrinin wa inna ila Rabbina lamunqalibun",
            translation = "Glorificado seja Aquele que submeteu isto a nós, pois nós não éramos capazes de dominá-lo, e por certo a nosso Senhor retornaremos.",
            source = "Alcorão 43:13-14, Sahih Muslim 2:978",
            repeat = 1
        ),
        DuaItem(
            id = 20,
            category = "travel",
            title = "Entering a Town or City",
            arabic = "اللَّهُمَّ رَبَّ السَّمَوَاتِ السَّبْعِ وَمَا أَظْلَلْنَ وَرَبَّ الْأَرَضِينَ السَّبْعِ وَمَا أَقْلَلْنَ وَرَبَّ الشَّيَاطِينِ وَمَا أَضْلَلْنَ وَرَبَّ الرِّيَاحِ وَمَا ذَرَيْنَ أَسْأَلُكَ خَيْرَ هَذِهِ الْقَرْيَةِ وَخَيْرَ أَهْلِهَا وَخَيْرَ مَا فِيهَا",
            transliteration = "Allahumma Rabbas-samawatis-sab'i wa ma adhlalna, wa Rabbal-aradeenas-sab'i wa ma aqlalna, as'aluka khayra hadhihil-qaryati wa khayra ahliha wa khayra ma fiha",
            translation = "Ó Allah, Senhor dos sete céus e tudo o que cobrem, Senhor das sete terras e tudo o que sustentam. Peço-Te o bem desta cidade, o bem de seu povo e o bem do que nela há.",
            source = "Al-Hakim 2:100, An-Nasai",
            repeat = 1
        ),
        DuaItem(
            id = 21,
            category = "home",
            title = "Leaving the Home",
            arabic = "بِسْمِ اللَّهِ تَوَكَّلْتُ عَلَى اللَّهِ وَلَا حَوْلَ وَلَا قُوَّةَ إِلَّا بِاللَّهِ",
            transliteration = "Bismillah, tawakkaltu 'alallah, wa la hawla wa la quwwata illa billah",
            translation = "Em nome de Allah, confio em Allah, e não há força nem poder senão com Allah.",
            source = "Abu Dawud 4:325, At-Tirmidhi 5:490",
            repeat = 1
        ),
        DuaItem(
            id = 22,
            category = "home",
            title = "Entering the Home",
            arabic = "بِسْمِ اللَّهِ وَلَجْنَا وَبِسْمِ اللَّهِ خَرَجْنَا وَعَلَى اللَّهِ رَبِّنَا تَوَكَّلْنَا",
            transliteration = "Bismillahi walajna, wa bismillahi kharajna, wa 'ala Allahi Rabbina tawakkalna",
            translation = "Em nome de Allah entramos, em nome de Allah saímos, e em nosso Senhor depositamos nossa confiança.",
            source = "Abu Dawud 4:325",
            repeat = 1
        ),
        DuaItem(
            id = 23,
            category = "masjid",
            title = "Entering the Masjid",
            arabic = "اللَّهُمَّ افْتَحْ لِي أَبْوَابَ رَحْمَتِكَ",
            transliteration = "Allahummaf-tah li abwaba rahmatik",
            translation = "Ó Allah, abre para mim as portas da Tua misericórdia.",
            source = "Sahih Muslim 1:494",
            repeat = 1
        ),
        DuaItem(
            id = 24,
            category = "masjid",
            title = "Leaving the Masjid",
            arabic = "اللَّهُمَّ إِنِّي أَسْأَلُكَ مِنْ فَضْلِكَ",
            transliteration = "Allahumma inni as'aluka min fadlik",
            translation = "Ó Allah, peço-Te de Tua generosidade e graça.",
            source = "Sahih Muslim 1:494",
            repeat = 1
        ),
        DuaItem(
            id = 25,
            category = "distress",
            title = "Dua for Distress (Alívio de Angústia)",
            arabic = "لَا إِلَهَ إِلَّا اللَّهُ الْعَظِيمُ الْحَلِيمُ لَا إِلَهَ إِلَّا اللَّهُ رَبُّ الْعَرْشِ الْعَظِيمِ لَا إِلَهَ إِلَّا اللَّهُ رَبُّ السَّمَوَاتِ وَرَبُّ الْأَرْضِ وَرَبُّ الْعَرْشِ الْكَرِيمِ",
            transliteration = "La ilaha illallahul-'Adheemul-Haleem, la ilaha illallahu Rabbul-'Arshil-'Adheem, la ilaha illallahu Rabbus-samawati wa Rabbul-ardi wa Rabbul-'Arshil-Kareem",
            translation = "Não há divindade além de Allah, o Magnífico, o Clemente. Não há divindade além de Allah, Senhor do Supremo Trono. Não há divindade além de Allah, Senhor dos céus, Senhor da terra e Senhor do Nobre Trono.",
            source = "Sahih Al-Bukhari 8:154, Sahih Muslim 4:2092",
            repeat = 1
        ),
        DuaItem(
            id = 26,
            category = "distress",
            title = "Dua of Yunus (Jonas no ventre da baleia)",
            arabic = "لَا إِلَهَ إِلَّا أَنْتَ سُبْحَانَكَ إِنِّي كُنْتُ مِنَ الظَّالِمِينَ",
            transliteration = "La ilaha illa anta subhanaka inni kuntu minadh-dhalimeen",
            translation = "Não há divindade além de Ti; glorificado sejas! Em verdade, estive entre os injustos.",
            source = "Alcorão 21:87, At-Tirmidhi",
            repeat = 1
        ),
        DuaItem(
            id = 27,
            category = "distress",
            title = "When Anxious or Worried",
            arabic = "اللَّهُمَّ إِنِّي أَعُوذُ بِكَ مِنَ الْهَمِّ وَالْحَزَنِ وَأَعُوذُ بِكَ مِنَ الْعَجْزِ وَالْكَسَلِ وَأَعُوذُ بِكَ مِنَ الْجُبْنِ وَالْبُخْلِ وَأَعُوذُ بِكَ مِنْ غَلَبَةِ الدَّيْنِ وَقَهْرِ الرِّجَالِ",
            transliteration = "Allahumma inni a'udhu bika minal-hammi wal-hazani, wa a'udhu bika minal-'ajzi wal-kasali, wa a'udhu bika minal-jubni wal-bukhli, wa a'udhu bika min ghalabatid-dayni wa qahrir-rijal",
            translation = "Ó Allah, busco refúgio em Ti contra a ansiedade e a tristeza, contra a incapacidade e a preguiça, contra a covardia e a mesquinhez, e contra o fardo das dívidas e a opressão dos homens.",
            source = "Sahih Al-Bukhari 7:158",
            repeat = 1
        ),
        DuaItem(
            id = 28,
            category = "forgiveness",
            title = "Seeking Forgiveness",
            arabic = "أَسْتَغْفِرُ اللَّهَ الْعَظِيمَ الَّذِي لَا إِلَهَ إِلَّا هُوَ الْحَيُّ الْقَيُّومُ وَأَتُوبُ إِلَيْهِ",
            transliteration = "Astaghfirullaha al-'Adheem alladhi la ilaha illa Huwal-Hayyul-Qayyum wa atubu ilayh",
            translation = "Peço perdão a Allah, o Grandioso, além de Quem não há divindade, o Vivente, o Sustentador, e a Ele me arrependo.",
            source = "Abu Dawud, At-Tirmidhi",
            repeat = 3
        ),
        DuaItem(
            id = 29,
            category = "forgiveness",
            title = "Comprehensive Forgiveness",
            arabic = "رَبَّنَا ظَلَمْنَا أَنْفُسَنَا وَإِنْ لَمْ تَغْفِرْ لَنَا وَتَرْحَمْنَا لَنَكُونَنَّ مِنَ الْخَاسِرِينَ",
            transliteration = "Rabbana dhalamna anfusana wa in lam taghfir lana wa tarhamna lanakoonanna minal-khasireen",
            translation = "Nosso Senhor! Fomos injustos para com nós mesmos, e se Tu não nos perdoares e não tiveres misericórdia de nós, certamente estaremos entre os perdedores.",
            source = "Alcorão 7:23",
            repeat = 1
        ),
        DuaItem(
            id = 30,
            category = "illness",
            title = "Visiting the Sick (Visita aos enfermos)",
            arabic = "لَا بَأْسَ طَهُورٌ إِنْ شَاءَ اللَّهُ",
            transliteration = "La ba'sa tahurun insha'Allah",
            translation = "Não há mal, é uma purificação, se Allah quiser.",
            source = "Sahih Al-Bukhari 7:372",
            repeat = 1
        ),
        DuaItem(
            id = 31,
            category = "illness",
            title = "Dua for Healing (Cura e Saúde)",
            arabic = "اللَّهُمَّ رَبَّ النَّاسِ أَذْهِبِ الْبَأْسَ اشْفِ أَنْتَ الشَّافِي لَا شِفَاءَ إِلَّا شِفَاؤُكَ شِفَاءً لَا يُغَادِرُ سَقَمًا",
            transliteration = "Allahumma Rabban-nasi, adhibil-ba'sa, ishfi antash-Shafi, la shifa'a illa shifa'uka, shifa'an la yughadiru saqama",
            translation = "Ó Allah, Senhor da humanidade, afasta a enfermidade. Cura, pois Tu és Aquele que cura. Não há cura exceto a Tua cura, uma cura que não deixa doença alguma.",
            source = "Sahih Al-Bukhari 7:379, Sahih Muslim",
            repeat = 1
        ),
        DuaItem(
            id = 32,
            category = "weather",
            title = "When It Rains (Chuva abençoada)",
            arabic = "اللَّهُمَّ صَيِّبًا نَافِعًا",
            transliteration = "Allahumma sayyiban nafi'a",
            translation = "Ó Allah, que seja uma chuva benéfica.",
            source = "Sahih Al-Bukhari",
            repeat = 1
        ),
        DuaItem(
            id = 33,
            category = "weather",
            title = "After Rain",
            arabic = "مُطِرْنَا بِفَضْلِ اللَّهِ وَرَحْمَتِهِ",
            transliteration = "Mutirna bifadlillahi wa rahmatihi",
            translation = "Recebemos chuva pela graça e misericórdia de Allah.",
            source = "Sahih Al-Bukhari 1:147, Sahih Muslim 1:58",
            repeat = 1
        ),
        DuaItem(
            id = 34,
            category = "weather",
            title = "During Thunder (Trovão)",
            arabic = "سُبْحَانَ الَّذِي يُسَبِّحُ الرَّعْدُ بِحَمْدِهِ وَالْمَلَائِكَةُ مِنْ خِيفَتِهِ",
            transliteration = "Subhanal-ladhi yusabbihur-ra'du bihamdihi wal-mala'ikatu min khifatihi",
            translation = "Glorificado seja Aquele a Quem o trovão glorifica com Seu louvor, e os anjos pelo temor d'Ele.",
            source = "Al-Muwatta' 2:992",
            repeat = 1
        ),
        DuaItem(
            id = 35,
            category = "knowledge",
            title = "Increase in Knowledge (Aumento de Conhecimento)",
            arabic = "رَبِّ زِدْنِي عِلْمًا",
            transliteration = "Rabbi zidni 'ilma",
            translation = "Meu Senhor, aumente-me em conhecimento.",
            source = "Alcorão 20:114",
            repeat = 1
        ),
        DuaItem(
            id = 36,
            category = "parents",
            title = "Dua for Parents (Pelos Pais)",
            arabic = "رَبِّ ارْحَمْهُمَا كَمَا رَبَّيَانِي صَغِيرًا",
            transliteration = "Rabbir-hamhuma kama rabbayani sagheera",
            translation = "Meu Senhor, tem misericórdia de ambos, assim como eles me criaram quando eu era pequeno.",
            source = "Alcorão 17:24",
            repeat = 1
        ),
        DuaItem(
            id = 37,
            category = "guidance",
            title = "Istikhara (Súplica de Orientação e Decisão)",
            arabic = "اللَّهُمَّ إِنِّي أَسْتَخِيرُكَ بِعِلْمِكَ وَأَسْتَقْدِرُكَ بِقُدْرَتِكَ وَأَسْأَلُكَ مِنْ فَضْلِكَ الْعَظِيمِ فَإِنَّكَ تَقْدِرُ وَلَا أَقْدِرُ وَتَعْلَمُ وَلَا أَعْلَمُ وَأَنْتَ عَلَّامُ الْغُيُوبِ",
            transliteration = "Allahumma inni astakheeruka bi'ilmika wa astaqdiruka biqudratika wa as'aluka min fadlikal-'adheem, fa innaka taqdiru wa la aqdir, wa ta'lamu wa la a'lam, wa anta 'allamul-ghuyub",
            translation = "Ó Allah, peço-Te orientação pelo Teu conhecimento e peço-Te capacidade pelo Teu poder, e peço-Te de Tua imensa generosidade. Pois Tu podes e eu não posso, Tu sabes e eu não sei, e Tu és o Conhecedor do oculto.",
            source = "Sahih Al-Bukhari 2:263",
            repeat = 1
        ),
        DuaItem(
            id = 38,
            category = "gratitude",
            title = "Thanking Allah (Agradecimento)",
            arabic = "الْحَمْدُ لِلَّهِ رَبِّ الْعَالَمِينَ",
            transliteration = "Alhamdu lillahi Rabbil-'alameen",
            translation = "Todo louvor pertence a Allah, Senhor do Universo.",
            source = "Alcorão 1:2",
            repeat = 1
        ),
        DuaItem(
            id = 39,
            category = "protection",
            title = "Protection from Evil Eye (Mau-Olhado)",
            arabic = "أَعُوذُ بِكَلِمَاتِ اللَّهِ التَّامَّةِ مِنْ كُلِّ شَيْطَانٍ وَهَامَّةٍ وَمِنْ كُلِّ عَيْنٍ لَامَّةٍ",
            transliteration = "A'udhu bikalimatillahit-tammati min kulli shaytanin wa hammatin wa min kulli 'aynin lammah",
            translation = "Busco refúgio nas palavras perfeitas de Allah de todo demônio e réptil venenoso, e de todo olho invejoso.",
            source = "Sahih Al-Bukhari 4:119",
            repeat = 1
        ),
        DuaItem(
            id = 40,
            category = "protection",
            title = "General Protection",
            arabic = "بِسْمِ اللَّهِ الَّذِي لَا يَضُرُّ مَعَ اسْمِهِ شَيْءٌ فِي الْأَرْضِ وَلَا فِي السَّمَاءِ وَهُوَ السَّمِيعُ الْعَلِيمُ",
            transliteration = "Bismillahilladhi la yadurru ma'asmihi shay'un fil-ardi wa la fis-sama'i wa huwas-sami'ul-'aleem",
            translation = "Em nome de Allah, com cujo nome nada na terra nem nos céus pode causar dano, e Ele é o Oniouvinte, o Onisciente.",
            source = "Abu Dawud 4:323",
            repeat = 3
        ),
        DuaItem(
            id = 41,
            category = "wudu",
            title = "Before Wudu (Antes da Abluções)",
            arabic = "بِسْمِ اللَّهِ",
            transliteration = "Bismillah",
            translation = "Em nome de Allah.",
            source = "Abu Dawud 1:73, At-Tirmidhi 1:5",
            repeat = 1
        ),
        DuaItem(
            id = 42,
            category = "wudu",
            title = "After Wudu (Testemunho)",
            arabic = "أَشْهَدُ أَنْ لَا إِلَهَ إِلَّا اللَّهُ وَحْدَهُ لَا شَرِيكَ لَهُ وَأَشْهَدُ أَنَّ مُحَمَّدًا عَبْدُهُ وَرَسُولُهُ",
            transliteration = "Ash-hadu an la ilaha illallahu wahdahu la shareeka lahu wa ash-hadu anna Muhammadan 'abduhu wa rasuluh",
            translation = "Testemunho que não há divindade digna de adoração exceto Allah, Único e sem parceiros, e testemunho que Muhammad é Seu servo e mensageiro.",
            source = "Sahih Muslim 1:234",
            repeat = 1
        ),
        DuaItem(
            id = 43,
            category = "wudu",
            title = "Complete After-Wudu Dua",
            arabic = "اللَّهُمَّ اجْعَلْنِي مِنَ التَّوَّابِينَ وَاجْعَلْنِي مِنَ الْمُتَطَهِّرِينَ",
            transliteration = "Allahumma-j'alni minat-tawwabina waj'alni minal-mutatahhirin",
            translation = "Ó Allah, faze-me daqueles que se arrependem com frequência e daqueles que se purificam.",
            source = "At-Tirmidhi 1:78",
            repeat = 1
        ),
        DuaItem(
            id = 46,
            category = "morning",
            title = "Morning Tasbih x100",
            arabic = "سُبْحَانَ اللَّهِ وَبِحَمْدِهِ",
            transliteration = "SubhanAllahi wa bihamdih",
            translation = "Glorificado seja Allah e com Seu louvor. (Quem recita 100x de manhã e à noite, ninguém trará feito melhor no Dia do Juízo).",
            source = "Sahih Al-Bukhari 8:412, Sahih Muslim 4:2071",
            repeat = 100
        ),
        DuaItem(
            id = 75,
            category = "distress",
            title = "Hasbunallah wa Ni'mal Wakil",
            arabic = "حَسْبُنَا اللَّهُ وَنِعْمَ الْوَكِيلُ",
            transliteration = "Hasbunallahu wa ni'mal-wakil",
            translation = "Allah nos é suficiente e Ele é o melhor Protetor e Administrador de nossos assuntos.",
            source = "Alcorão 3:173, Sahih Al-Bukhari 8:69",
            repeat = 1
        ),
        DuaItem(
            id = 76,
            category = "distress",
            title = "La Hawla Wa La Quwwata (Tesouro do Paraíso)",
            arabic = "لَا حَوْلَ وَلَا قُوَّةَ إِلَّا بِاللَّهِ",
            transliteration = "La hawla wa la quwwata illa billah",
            translation = "Não há força nem poder exceto com Allah.",
            source = "Sahih Al-Bukhari 7:158, Sahih Muslim 4:2076",
            repeat = 1
        ),
        DuaItem(
            id = 78,
            category = "forgiveness",
            title = "Dua for Laylat al-Qadr (Noite do Decreto)",
            arabic = "اللَّهُمَّ إِنَّكَ عَفُوٌّ تُحِبُّ الْعَفْوَ فَاعْفُ عَنِّي",
            transliteration = "Allahumma innaka 'afuwwun tuhibbul-'afwa fa'fu 'anni",
            translation = "Ó Allah, Tu és Perdoador e amas o perdão, portanto perdoa-me.",
            source = "At-Tirmidhi 5:534, Ibn Majah 2:1265",
            repeat = 1
        ),
        DuaItem(
            id = 88,
            category = "guidance",
            title = "Dua for Steadfastness (Firmeza no coração)",
            arabic = "يَا مُقَلِّبَ الْقُلُوبِ ثَبِّتْ قَلْبِي عَلَى دِينِكَ",
            transliteration = "Ya Muqallibal-qulub, thabbit qalbi 'ala dinik",
            translation = "Ó Controlador dos corações, firma o meu coração na Tua religião.",
            source = "At-Tirmidhi 4:447, Ahmad",
            repeat = 1
        ),
        DuaItem(
            id = 94,
            category = "dhikr",
            title = "SubhanAllah wa Bihamdihi x100 (Perdão dos Pecados)",
            arabic = "سُبْحَانَ اللَّهِ وَبِحَمْدِهِ",
            transliteration = "SubhanAllahi wa bihamdih",
            translation = "Glória a Allah e com Seu louvor. (Quem diz 100x ao dia terá seus pecados perdoados mesmo que sejam como a espuma do mar).",
            source = "Sahih Al-Bukhari 8:414, Sahih Muslim 4:2071",
            repeat = 100
        ),
        DuaItem(
            id = 95,
            category = "dhikr",
            title = "The Best Dhikr (O Melhor Dhikr)",
            arabic = "لَا إِلَهَ إِلَّا اللَّهُ",
            transliteration = "La ilaha illallah",
            translation = "Não há divindade digna de adoração exceto Allah.",
            source = "At-Tirmidhi 5:462, Ibn Majah",
            repeat = 1
        ),
        DuaItem(
            id = 96,
            category = "dhikr",
            title = "Salawat on the Prophet (Paz e Bênçãos)",
            arabic = "اللَّهُمَّ صَلِّ عَلَى مُحَمَّدٍ وَعَلَى آلِ مُحَمَّدٍ",
            transliteration = "Allahumma salli 'ala Muhammadin wa 'ala ali Muhammad",
            translation = "Ó Allah, derrama Tuas bênçãos sobre Muhammad e sobre a família de Muhammad.",
            source = "Sahih Muslim 1:408, At-Tirmidhi 2:354",
            repeat = 10
        ),
        DuaItem(
            id = 98,
            category = "dhikr",
            title = "Two Words Heavy on the Scales",
            arabic = "سُبْحَانَ اللَّهِ الْعَظِيمِ وَبِحَمْدِهِ",
            transliteration = "SubhanAllahil-'Adheem wa bihamdih",
            translation = "Glória a Allah o Grandioso e com Seu louvor. (Duas palavras leves na língua, pesadas na balança e amadas pelo Misericordioso).",
            source = "Sahih Al-Bukhari 8:417, Sahih Muslim 4:2072",
            repeat = 1
        ),
        DuaItem(
            id = 102,
            category = "marriage",
            title = "Dua for a Righteous Spouse & Family",
            arabic = "رَبَّنَا هَبْ لَنَا مِنْ أَزْوَاجِنَا وَذُرِّيَّاتِنَا قُرَّةَ أَعْيُنٍ وَاجْعَلْنَا لِلْمُتَّقِينَ إِمَامًا",
            transliteration = "Rabbana hab lana min azwajina wa dhurriyyatina qurrata a'yunin waj'alna lil-muttaqina imama",
            translation = "Nosso Senhor! Concede-nos, em nossas esposas e descendência, o conforto dos nossos olhos, e faz-nos um guia para os virtuosos.",
            source = "Alcorão 25:74",
            repeat = 1
        ),
        DuaItem(
            id = 104,
            category = "hajj",
            title = "Talbiyah (Hajj & Umrah)",
            arabic = "لَبَّيْكَ اللَّهُمَّ لَبَّيْكَ لَبَّيْكَ لَا شَرِيكَ لَكَ لَبَّيْكَ إِنَّ الْحَمْدَ وَالنِّعْمَةَ لَكَ وَالْمُلْكَ لَا شَرِيكَ لَكَ",
            transliteration = "Labbayk Allahumma labbayk, labbayk la shareeka laka labbayk, innal-hamda wan-ni'mata laka wal-mulk, la shareeka lak",
            translation = "Eis-me aqui, ó Allah, eis-me aqui! Eis-me aqui, não tens parceiro algum, eis-me aqui! Por certo todo louvor, graça e soberania pertencem a Ti.",
            source = "Sahih Al-Bukhari 2:798, Sahih Muslim 2:841",
            repeat = 1
        ),
        DuaItem(
            id = 108,
            category = "grief",
            title = "Inna Lillahi (Diante de Calamidades e Perdas)",
            arabic = "إِنَّا لِلَّهِ وَإِنَّا إِلَيْهِ رَاجِعُونَ اللَّهُمَّ أْجُرْنِي فِي مُصِيبَتِي وَأَخْلِفْ لِي خَيْرًا مِنْهَا",
            transliteration = "Inna lillahi wa inna ilayhi raji'un, Allahumma-jurni fi musibati wa akhlif li khayran minha",
            translation = "Por certo pertencemos a Allah e a Ele retornaremos. Ó Allah, recompensa-me em minha aflição e concede-me em troca algo ainda melhor.",
            source = "Sahih Muslim 2:632",
            repeat = 1
        ),
        DuaItem(
            id = 113,
            category = "children",
            title = "Dua for Righteous Children (Filhos Virtuosos)",
            arabic = "رَبِّ هَبْ لِي مِنْ لَدُنْكَ ذُرِّيَّةً طَيِّبَةً إِنَّكَ سَمِيعُ الدُّعَاءِ",
            transliteration = "Rabbi hab li min ladunka dhurriyyatan tayyibatan innaka sami'ud-du'a",
            translation = "Meu Senhor, concede-me de Tua parte uma descendência virtuosa. Por certo, Tu és o Ouvinte das súplicas.",
            source = "Alcorão 3:38",
            repeat = 1
        ),
        DuaItem(
            id = 116,
            category = "business",
            title = "Dua for Halal Provision & Freedom from Debt (Sustento & Riqueza)",
            arabic = "اللَّهُمَّ اكْفِنِي بِحَلَالِكَ عَنْ حَرَامِكَ وَأَغْنِنِي بِفَضْلِكَ عَمَّنْ سِوَاكَ",
            transliteration = "Allahummak-fini bihalali 'an haramika wa aghnini bifadlika 'amman siwak",
            translation = "Ó Allah, basta-me com o que tornaste lícito contra o que tornaste ilícito, e enriquece-me com a Tua graça tornando-me independente de qualquer outro além de Ti.",
            source = "At-Tirmidhi 5:560",
            repeat = 1
        ),
        DuaItem(
            id = 120,
            category = "night_prayer",
            title = "Dua at Tahajjud (Oração Noturna)",
            arabic = "اللَّهُمَّ لَكَ الْحَمْدُ أَنْتَ نُورُ السَّمَوَاتِ وَالْأَرْضِ وَمَنْ فِيهِنَّ وَلَكَ الْحَمْدُ أَنْتَ قَيِّمُ السَّمَوَاتِ وَالْأَرْضِ وَمَنْ فِيهِنَّ",
            transliteration = "Allahumma lakal-hamdu anta nurus-samawati wal-ardi wa man fihinn, wa lakal-hamdu anta qayyimus-samawati wal-ardi wa man fihinn",
            translation = "Ó Allah, a Ti pertence todo o louvor; Tu és a Luz dos céus e da terra e de tudo o que neles há. A Ti pertence todo o louvor; Tu és o Sustentador dos céus e da terra e de tudo o que neles há.",
            source = "Sahih Al-Bukhari 2:324, Sahih Muslim 1:769",
            repeat = 1
        ),
        DuaItem(
            id = 125,
            category = "quran_recitation",
            title = "Al-Fatiha (A Abertura do Nobre Alcorão)",
            arabic = "بِسْمِ اللَّهِ الرَّحْمَنِ الرَّحِيمِ الْحَمْدُ لِلَّهِ رَبِّ الْعَالَمِينَ الرَّحْمَنِ الرَّحِيمِ مَالِكِ يَوْمِ الدِّينِ إِيَّاكَ نَعْبُدُ وَإِيَّاكَ نَسْتَعِينُ اهْدِنَا الصِّرَاطَ الْمُسْتَقِيمَ صِرَاطَ الَّذِينَ أَنْعَمْتَ عَلَيْهِمْ غَيْرِ الْمَغْضُوبِ عَلَيْهِمْ وَلَا الضَّالِّينَ",
            transliteration = "Bismillahir-Rahmanir-Rahim, Alhamdu lillahi Rabbil-'alamin, Ar-Rahmanir-Rahim, Maliki yawmid-din, Iyyaka na'budu wa iyyaka nasta'in, Ihdinas-siratal-mustaqim, Siratal-ladhina an'amta 'alayhim, ghayril-maghdubi 'alayhim wa lad-dallin",
            translation = "Em nome de Allah, o Clemente, o Misericordioso. Louvado seja Allah, Senhor do Universo, o Clemente, o Misericordioso, Soberano do Dia do Juízo. Só a Ti adoramos e só a Ti suplicamos ajuda. Guia-nos à senda reta, a senda dos que agraciaste, não a dos que incorreram na Tua ira, nem a dos extraviados.",
            source = "Alcorão 1:1-7",
            repeat = 1
        )
    )
}
