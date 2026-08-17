package com.example.util

enum class AppLanguage(val code: String, val displayName: String, val nativeName: String, val isRtl: Boolean = false) {
    PORTUGUESE("pt", "Português", "Português", false),
    ENGLISH("en", "English", "English", false),
    FRENCH("fr", "Français", "Français", false),
    ARABIC("ar", "Árabe", "العربية", true),
    SPANISH("es", "Espanhol", "Español", false);

    companion object {
        val PT get() = PORTUGUESE
        val EN get() = ENGLISH
        val FR get() = FRENCH
        val ES get() = SPANISH
        val AR get() = ARABIC

        fun fromCode(code: String): AppLanguage =
            values().firstOrNull { it.code.equals(code, ignoreCase = true) } ?: PORTUGUESE
    }
}

object AppStrings {
    // --- MAIN NAVIGATION ---
    fun tabHome(lang: AppLanguage): String = when (lang) {
        AppLanguage.PORTUGUESE -> "Início"
        AppLanguage.ENGLISH -> "Home"
        AppLanguage.FRENCH -> "Accueil"
        AppLanguage.ARABIC -> "الرئيسية"
        AppLanguage.SPANISH -> "Inicio"
    }

    fun tabPrayer(lang: AppLanguage): String = when (lang) {
        AppLanguage.PORTUGUESE -> "Salat"
        AppLanguage.ENGLISH -> "Salat"
        AppLanguage.FRENCH -> "Salat"
        AppLanguage.ARABIC -> "الصلاة"
        AppLanguage.SPANISH -> "Salat"
    }

    fun tabQuran(lang: AppLanguage): String = when (lang) {
        AppLanguage.PORTUGUESE -> "Qur'an"
        AppLanguage.ENGLISH -> "Qur'an"
        AppLanguage.FRENCH -> "Coran"
        AppLanguage.ARABIC -> "القرآن"
        AppLanguage.SPANISH -> "Corán"
    }

    fun tabTasbih(lang: AppLanguage): String = when (lang) {
        AppLanguage.PORTUGUESE -> "Tasbih"
        AppLanguage.ENGLISH -> "Tasbih"
        AppLanguage.FRENCH -> "Tasbih"
        AppLanguage.ARABIC -> "التسبيح"
        AppLanguage.SPANISH -> "Tasbih"
    }

    fun tabCalendar(lang: AppLanguage): String = when (lang) {
        AppLanguage.PORTUGUESE -> "Calendário"
        AppLanguage.ENGLISH -> "Calendar"
        AppLanguage.FRENCH -> "Calendrier"
        AppLanguage.ARABIC -> "التقويم"
        AppLanguage.SPANISH -> "Calendario"
    }

    fun tabSettings(lang: AppLanguage): String = when (lang) {
        AppLanguage.PORTUGUESE -> "Ajustes"
        AppLanguage.ENGLISH -> "Settings"
        AppLanguage.FRENCH -> "Réglages"
        AppLanguage.ARABIC -> "الإعدادات"
        AppLanguage.SPANISH -> "Ajustes"
    }

    fun tabDuas(lang: AppLanguage): String = when (lang) {
        AppLanguage.PORTUGUESE -> "Duas"
        AppLanguage.ENGLISH -> "Duas"
        AppLanguage.FRENCH -> "Invocations"
        AppLanguage.ARABIC -> "الأدعية"
        AppLanguage.SPANISH -> "Súplicas"
    }

    fun tabHadith(lang: AppLanguage): String = when (lang) {
        AppLanguage.PORTUGUESE -> "Hadith"
        AppLanguage.ENGLISH -> "Hadith"
        AppLanguage.FRENCH -> "Hadiths"
        AppLanguage.ARABIC -> "الحديث"
        AppLanguage.SPANISH -> "Hadiz"
    }

    fun tabGeneral(lang: AppLanguage): String = when (lang) {
        AppLanguage.PORTUGUESE -> "Geral"
        AppLanguage.ENGLISH -> "General"
        AppLanguage.FRENCH -> "Général"
        AppLanguage.ARABIC -> "عام"
        AppLanguage.SPANISH -> "General"
    }

    fun favorites(lang: AppLanguage): String = when (lang) {
        AppLanguage.PORTUGUESE -> "Favoritos"
        AppLanguage.ENGLISH -> "Favorites"
        AppLanguage.FRENCH -> "Favoris"
        AppLanguage.ARABIC -> "المفضلة"
        AppLanguage.SPANISH -> "Favoritos"
    }

    // --- HOME SCREEN ---
    fun prayerSummaryTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.PORTUGUESE -> "Horários de Salah (Oração)"
        AppLanguage.ENGLISH -> "Salah (Prayer) Times"
        AppLanguage.FRENCH -> "Horaires de Prière (Salat)"
        AppLanguage.ARABIC -> "أوقات الصلاة"
        AppLanguage.SPANISH -> "Horarios de Salah (Oración)"
    }

    fun nextPrayerLabel(lang: AppLanguage, prayer: String, timeLeft: String): String = when (lang) {
        AppLanguage.PORTUGUESE -> "Próxima: $prayer em $timeLeft"
        AppLanguage.ENGLISH -> "Next: $prayer in $timeLeft"
        AppLanguage.FRENCH -> "Prochaine: $prayer dans $timeLeft"
        AppLanguage.ARABIC -> "الصلاة القادمة: $prayer خلال $timeLeft"
        AppLanguage.SPANISH -> "Próxima: $prayer en $timeLeft"
    }

    fun tapForFullSchedule(lang: AppLanguage): String = when (lang) {
        AppLanguage.PORTUGUESE -> "Toque para ver o cronograma completo"
        AppLanguage.ENGLISH -> "Tap to view full prayer schedule"
        AppLanguage.FRENCH -> "Touchez pour voir les horaires complets"
        AppLanguage.ARABIC -> "انقر لعرض الجدول الكامل"
        AppLanguage.SPANISH -> "Toque para ver el cronograma completo"
    }

    fun servicesAndDevotion(lang: AppLanguage): String = when (lang) {
        AppLanguage.PORTUGUESE -> "Serviços & Devoção"
        AppLanguage.ENGLISH -> "Services & Devotion"
        AppLanguage.FRENCH -> "Services & Dévotion"
        AppLanguage.ARABIC -> "الخدمات والعبادات"
        AppLanguage.SPANISH -> "Servicios y Devoción"
    }

    fun quickAccessSubtitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.PORTUGUESE -> "Acesso rápido às práticas diárias"
        AppLanguage.ENGLISH -> "Quick access to daily spiritual practices"
        AppLanguage.FRENCH -> "Accès rapide aux pratiques quotidiennes"
        AppLanguage.ARABIC -> "وصول سريع للأذكار والعبادات"
        AppLanguage.SPANISH -> "Acceso rápido a las prácticas diarias"
    }

    fun dailyAdhkar(lang: AppLanguage): String = when (lang) {
        AppLanguage.PORTUGUESE -> "Adhkar Diários"
        AppLanguage.ENGLISH -> "Daily Adhkar"
        AppLanguage.FRENCH -> "Adhkar Quotidiens"
        AppLanguage.ARABIC -> "أذكار اليوم"
        AppLanguage.SPANISH -> "Adhkar Diarios"
    }

    fun supplications(lang: AppLanguage): String = when (lang) {
        AppLanguage.PORTUGUESE -> "Súplicas"
        AppLanguage.ENGLISH -> "Supplications"
        AppLanguage.FRENCH -> "Invocations"
        AppLanguage.ARABIC -> "أدعية"
        AppLanguage.SPANISH -> "Súplicas"
    }

    fun hijriEvents(lang: AppLanguage): String = when (lang) {
        AppLanguage.PORTUGUESE -> "Eventos Hijri"
        AppLanguage.ENGLISH -> "Hijri Events"
        AppLanguage.FRENCH -> "Événements Hijri"
        AppLanguage.ARABIC -> "المناسبات الهجرية"
        AppLanguage.SPANISH -> "Eventos Hiyri"
    }

    fun calendar(lang: AppLanguage): String = when (lang) {
        AppLanguage.PORTUGUESE -> "Calendário"
        AppLanguage.ENGLISH -> "Calendar"
        AppLanguage.FRENCH -> "Calendrier"
        AppLanguage.ARABIC -> "التقويم"
        AppLanguage.SPANISH -> "Calendario"
    }

    fun counter(lang: AppLanguage): String = when (lang) {
        AppLanguage.PORTUGUESE -> "Contador"
        AppLanguage.ENGLISH -> "Counter"
        AppLanguage.FRENCH -> "Compteur"
        AppLanguage.ARABIC -> "العداد"
        AppLanguage.SPANISH -> "Contador"
    }

    fun inspiringHadith(lang: AppLanguage): String = when (lang) {
        AppLanguage.PORTUGUESE -> "Hadith Inspirador"
        AppLanguage.ENGLISH -> "Inspiring Hadith"
        AppLanguage.FRENCH -> "Hadith Inspirant"
        AppLanguage.ARABIC -> "حديث شريف مُلهم"
        AppLanguage.SPANISH -> "Hadiz Inspirador"
    }

    fun authenticSources(lang: AppLanguage): String = when (lang) {
        AppLanguage.PORTUGUESE -> "Das nobres fontes autênticas"
        AppLanguage.ENGLISH -> "From noble authentic sources"
        AppLanguage.FRENCH -> "Des sources nobles et authentiques"
        AppLanguage.ARABIC -> "من المصادر النبوية الصحيحة"
        AppLanguage.SPANISH -> "De las nobles fuentes auténticas"
    }

    fun viewCollections(lang: AppLanguage): String = when (lang) {
        AppLanguage.PORTUGUESE -> "Ver Coleções"
        AppLanguage.ENGLISH -> "View Collections"
        AppLanguage.FRENCH -> "Voir Collections"
        AppLanguage.ARABIC -> "عرض المجموعات"
        AppLanguage.SPANISH -> "Ver Colecciones"
    }

    fun featuredDua(lang: AppLanguage): String = when (lang) {
        AppLanguage.PORTUGUESE -> "Súplica em Destaque"
        AppLanguage.ENGLISH -> "Featured Dua"
        AppLanguage.FRENCH -> "Invocation du Jour"
        AppLanguage.ARABIC -> "دعاء اليوم المختار"
        AppLanguage.SPANISH -> "Súplica Destacada"
    }

    fun masterOfForgiveness(lang: AppLanguage): String = when (lang) {
        AppLanguage.PORTUGUESE -> "Sayyid al-Istighfar (Mestre do Perdão)"
        AppLanguage.ENGLISH -> "Sayyid al-Istighfar (Master of Forgiveness)"
        AppLanguage.FRENCH -> "Sayyid al-Istighfar (Maître du Pardon)"
        AppLanguage.ARABIC -> "سيد الاستغفار"
        AppLanguage.SPANISH -> "Sayyid al-Istighfar (Señor del Perdón)"
    }

    fun viewAllDuas(lang: AppLanguage): String = when (lang) {
        AppLanguage.PORTUGUESE -> "Ver 126 Duas"
        AppLanguage.ENGLISH -> "View 126 Duas"
        AppLanguage.FRENCH -> "Voir 126 Invocations"
        AppLanguage.ARABIC -> "عرض ١٢٦ دعاء"
        AppLanguage.SPANISH -> "Ver 126 Súplicas"
    }

    fun recite(lang: AppLanguage): String = when (lang) {
        AppLanguage.PORTUGUESE -> "Recitar"
        AppLanguage.ENGLISH -> "Recite"
        AppLanguage.FRENCH -> "Réciter"
        AppLanguage.ARABIC -> "تكرار"
        AppLanguage.SPANISH -> "Recitar"
    }

    fun completed(lang: AppLanguage): String = when (lang) {
        AppLanguage.PORTUGUESE -> "Concluído"
        AppLanguage.ENGLISH -> "Completed"
        AppLanguage.FRENCH -> "Terminé"
        AppLanguage.ARABIC -> "مكتمل"
        AppLanguage.SPANISH -> "Completado"
    }

    fun grade(lang: AppLanguage): String = when (lang) {
        AppLanguage.PORTUGUESE -> "Classificação"
        AppLanguage.ENGLISH -> "Grade"
        AppLanguage.FRENCH -> "Degré"
        AppLanguage.ARABIC -> "درجة الحديث"
        AppLanguage.SPANISH -> "Clasificación"
    }

    // --- PRAYER TIMES SCREEN ---
    fun changeCity(lang: AppLanguage): String = when (lang) {
        AppLanguage.PORTUGUESE -> "Alterar Cidade"
        AppLanguage.ENGLISH -> "Change City"
        AppLanguage.FRENCH -> "Changer de Ville"
        AppLanguage.ARABIC -> "تغيير المدينة"
        AppLanguage.SPANISH -> "Cambiar Ciudad"
    }

    fun calculationMethod(lang: AppLanguage): String = when (lang) {
        AppLanguage.PORTUGUESE -> "Método de Cálculo"
        AppLanguage.ENGLISH -> "Calculation Method"
        AppLanguage.FRENCH -> "Méthode de Calcul"
        AppLanguage.ARABIC -> "طريقة الحساب"
        AppLanguage.SPANISH -> "Método de Cálculo"
    }

    fun refresh(lang: AppLanguage): String = when (lang) {
        AppLanguage.PORTUGUESE -> "Atualizar"
        AppLanguage.ENGLISH -> "Refresh"
        AppLanguage.FRENCH -> "Actualiser"
        AppLanguage.ARABIC -> "تحديث"
        AppLanguage.SPANISH -> "Actualizar"
    }

    // --- GENERAL HUB SCREEN ---
    fun generalHubTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.PORTUGUESE -> "Central Geral"
        AppLanguage.ENGLISH -> "General Hub"
        AppLanguage.FRENCH -> "Centre Général"
        AppLanguage.ARABIC -> "المركز العام"
        AppLanguage.SPANISH -> "Centro General"
    }

    fun generalHubSubtitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.PORTUGUESE -> "Ferramentas, Recursos & Configurações"
        AppLanguage.ENGLISH -> "Tools, Resources & Settings"
        AppLanguage.FRENCH -> "Outils, Ressources & Paramètres"
        AppLanguage.ARABIC -> "أدوات ومصادر وإعدادات إسلامية"
        AppLanguage.SPANISH -> "Herramientas, Recursos y Configuración"
    }

    fun islamicToolsSectionHeader(lang: AppLanguage): String = when (lang) {
        AppLanguage.PORTUGUESE -> "RECURSOS & FERRAMENTAS ISLÂMICAS"
        AppLanguage.ENGLISH -> "ISLAMIC TOOLS & RESOURCES"
        AppLanguage.FRENCH -> "OUTILS & RESSOURCES ISLAMIQUES"
        AppLanguage.ARABIC -> "المصادر والأدوات الإسلامية"
        AppLanguage.SPANISH -> "RECURSOS Y HERRAMIENTAS ISLÁMICAS"
    }

    fun backToGeneral(lang: AppLanguage): String = when (lang) {
        AppLanguage.PORTUGUESE -> "Voltar para Geral"
        AppLanguage.ENGLISH -> "Back to General"
        AppLanguage.FRENCH -> "Retour au Général"
        AppLanguage.ARABIC -> "الرجوع إلى عام"
        AppLanguage.SPANISH -> "Volver a General"
    }

    // Card details in General Hub
    fun quranTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.PORTUGUESE -> "Sagrado Alcorão"
        AppLanguage.ENGLISH -> "The Holy Quran"
        AppLanguage.FRENCH -> "Le Saint Coran"
        AppLanguage.ARABIC -> "القرآن الكريم"
        AppLanguage.SPANISH -> "El Sagrado Corán"
    }

    fun quranDesc(lang: AppLanguage): String = when (lang) {
        AppLanguage.PORTUGUESE -> "114 Suratas completas com áudio de 11 recitadores, modo Juz e comparação de versículos semelhantes (Mutashabihat)."
        AppLanguage.ENGLISH -> "114 complete Surahs with audio by 11 reciters, Juz mode and similar verse comparison (Mutashabihat)."
        AppLanguage.FRENCH -> "114 Sourates complètes avec l'audio de 11 récitateurs, mode Juz et versets similaires (Mutashabihat)."
        AppLanguage.ARABIC -> "١١٤ سورة كاملة مع تلاوات صوتية لـ ١١ قارئاً، وتقسيم الأجزاء والمتشابهات القرآنية."
        AppLanguage.SPANISH -> "114 Suras completas con audio de 11 recitadores, modo Juz y versículos semejantes (Mutashabihat)."
    }

    fun quranBadge(lang: AppLanguage): String = when (lang) {
        AppLanguage.PORTUGUESE -> "114 Suratas & Áudio"
        AppLanguage.ENGLISH -> "114 Surahs & Audio"
        AppLanguage.FRENCH -> "114 Sourates & Audio"
        AppLanguage.ARABIC -> "١١٤ سورة وتلاوات"
        AppLanguage.SPANISH -> "114 Suras y Audio"
    }

    fun tafsirTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.PORTUGUESE -> "Tafsir (Exegese)"
        AppLanguage.ENGLISH -> "Tafsir (Exegesis)"
        AppLanguage.FRENCH -> "Tafsir (Exégèse)"
        AppLanguage.ARABIC -> "تفسير القرآن الكريم"
        AppLanguage.SPANISH -> "Tafsir (Exégesis)"
    }

    fun tafsirDesc(lang: AppLanguage): String = when (lang) {
        AppLanguage.PORTUGUESE -> "Exegese detalhada de cada versículo com comentários de Ibn Kathir, Ma'arif al-Qur'an e Tafsir Muyassar."
        AppLanguage.ENGLISH -> "Detailed exegesis of every verse with commentaries by Ibn Kathir, Ma'arif al-Qur'an and Tafsir Muyassar."
        AppLanguage.FRENCH -> "Exégèse détaillée de chaque verset avec les commentaires d'Ibn Kathir, Ma'arif al-Qur'an et Tafsir Muyassar."
        AppLanguage.ARABIC -> "تفسير تفصيلي لكل آية كريمة باعتماد تفاسير ابن كثير ومعارف القرآن والميسر."
        AppLanguage.SPANISH -> "Exégesis detallada de cada versículo con comentarios de Ibn Kathir, Ma'arif al-Qur'an y Tafsir Muyassar."
    }

    fun tafsirBadge(lang: AppLanguage): String = when (lang) {
        AppLanguage.PORTUGUESE -> "Exegese Acadêmica"
        AppLanguage.ENGLISH -> "Scholarly Exegesis"
        AppLanguage.FRENCH -> "Exégèse Académique"
        AppLanguage.ARABIC -> "تفسير معتمد"
        AppLanguage.SPANISH -> "Exégesis Académica"
    }

    fun qiblaTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.PORTUGUESE -> "Direção da Qibla"
        AppLanguage.ENGLISH -> "Qibla Direction"
        AppLanguage.FRENCH -> "Direction de la Qibla"
        AppLanguage.ARABIC -> "اتجاه القبلة"
        AppLanguage.SPANISH -> "Dirección de la Qibla"
    }

    fun qiblaDesc(lang: AppLanguage): String = when (lang) {
        AppLanguage.PORTUGUESE -> "Bússola digital em tempo real com ângulo exato, distância em km até a Sagrada Kaaba e sensor de alinhamento tátil."
        AppLanguage.ENGLISH -> "Real-time digital compass with exact angle, distance in km to the Holy Kaaba and haptic alignment sensor."
        AppLanguage.FRENCH -> "Boussole numérique en temps réel avec angle exact, distance en km vers la Kaaba et capteur d'alignement haptique."
        AppLanguage.ARABIC -> "بوصلة رقمية دقيقة لتحديد اتجاه الكعبة المشرفة في مكة مع قياس المسافة بالكيلومترات."
        AppLanguage.SPANISH -> "Brújula digital en tiempo real con ángulo exacto, distancia en km hasta la Sagrada Kaaba y sensor háptico."
    }

    fun qiblaBadge(lang: AppLanguage): String = when (lang) {
        AppLanguage.PORTUGUESE -> "Bússola & Distância"
        AppLanguage.ENGLISH -> "Compass & Distance"
        AppLanguage.FRENCH -> "Boussole & Distance"
        AppLanguage.ARABIC -> "بوصلة والمسافة"
        AppLanguage.SPANISH -> "Brújula y Distancia"
    }

    fun asmaTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.PORTUGUESE -> "99 Nomes de Allah"
        AppLanguage.ENGLISH -> "99 Names of Allah"
        AppLanguage.FRENCH -> "99 Noms d'Allah"
        AppLanguage.ARABIC -> "أسماء الله الحسنى"
        AppLanguage.SPANISH -> "99 Nombres de Alá"
    }

    fun asmaDesc(lang: AppLanguage): String = when (lang) {
        AppLanguage.PORTUGUESE -> "Os 99 belos nomes de Allah com caligrafia árabe, transliteração, significado, virtudes e hadith profético."
        AppLanguage.ENGLISH -> "The 99 beautiful names of Allah with Arabic calligraphy, transliteration, meanings, virtues and prophetic Hadith."
        AppLanguage.FRENCH -> "Les 99 beaux noms d'Allah avec calligraphie arabe, translittération, significations, vertus et Hadith prophétique."
        AppLanguage.ARABIC -> "أسماء الله الحسنى بالخط العربي الجميل، مع المعاني والفضائل والحديث النبوي الشريف."
        AppLanguage.SPANISH -> "Los 99 bellos nombres de Alá con caligrafía árabe, transliteración, significados, virtudes y Hadiz profético."
    }

    fun asmaBadge(lang: AppLanguage): String = when (lang) {
        AppLanguage.PORTUGUESE -> "99 Nomes Sagrados"
        AppLanguage.ENGLISH -> "99 Sacred Names"
        AppLanguage.FRENCH -> "99 Noms Sacrés"
        AppLanguage.ARABIC -> "٩٩ اسماً مقدساً"
        AppLanguage.SPANISH -> "99 Nombres Sagrados"
    }

    fun namesTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.PORTUGUESE -> "Nomes Islâmicos"
        AppLanguage.ENGLISH -> "Islamic Names"
        AppLanguage.FRENCH -> "Noms Islamiques"
        AppLanguage.ARABIC -> "دليل الأسماء الإسلامية"
        AppLanguage.SPANISH -> "Nombres Islámicos"
    }

    fun namesDesc(lang: AppLanguage): String = when (lang) {
        AppLanguage.PORTUGUESE -> "Dicionário de nomes islâmicos masculinos e femininos, significados profundos, raízes árabes e gerador de sugestões."
        AppLanguage.ENGLISH -> "Dictionary of Islamic male and female names, deep meanings, Arabic roots and suggestion generator."
        AppLanguage.FRENCH -> "Dictionnaire des prénoms islamiques masculins et féminins, significations, racines arabes et suggestions."
        AppLanguage.ARABIC -> "معجم الأسماء الإسلامية للذكور والإناث مع المعاني اللغوية والجذور العربية واقتراحات مباركة."
        AppLanguage.SPANISH -> "Diccionario de nombres islámicos masculinos y femeninos, significados, raíces árabes y generador de sugerencias."
    }

    fun namesBadge(lang: AppLanguage): String = when (lang) {
        AppLanguage.PORTUGUESE -> "Dicionário & Raiz"
        AppLanguage.ENGLISH -> "Dictionary & Root"
        AppLanguage.FRENCH -> "Dictionnaire & Racine"
        AppLanguage.ARABIC -> "المعجم والجذور"
        AppLanguage.SPANISH -> "Diccionario y Raíz"
    }

    fun moonTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.PORTUGUESE -> "Calendário Lunar"
        AppLanguage.ENGLISH -> "Lunar Calendar"
        AppLanguage.FRENCH -> "Calendrier Lunaire"
        AppLanguage.ARABIC -> "التقويم القمري والأهلة"
        AppLanguage.SPANISH -> "Calendario Lunar"
    }

    fun moonDesc(lang: AppLanguage): String = when (lang) {
        AppLanguage.PORTUGUESE -> "Idade da lua, percentual de iluminação, visibilidade do crescente e previsão das 12 próximas conjunções lunares."
        AppLanguage.ENGLISH -> "Moon age, illumination percentage, crescent visibility and forecast for the next 12 new moon conjunctions."
        AppLanguage.FRENCH -> "Âge de la lune, pourcentage d'illumination, visibilité du croissant et prévision des 12 prochaines nouvelles lunes."
        AppLanguage.ARABIC -> "عمر القمر ونسبة الإضاءة وظروف رؤية الهلال وتوقعات المحاق والشهور لـ ١٢ شهراً قادماً."
        AppLanguage.SPANISH -> "Edad de la luna, porcentaje de iluminación, visibilidad del creciente y previsión de las próximas 12 lunas nuevas."
    }

    fun moonBadge(lang: AppLanguage): String = when (lang) {
        AppLanguage.PORTUGUESE -> "Fases & Visibilidade"
        AppLanguage.ENGLISH -> "Phases & Visibility"
        AppLanguage.FRENCH -> "Phases & Visibilité"
        AppLanguage.ARABIC -> "الأطوار والرؤية"
        AppLanguage.SPANISH -> "Fases y Visibilidad"
    }

    fun tasbihTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.PORTUGUESE -> "Tasbih Digital"
        AppLanguage.ENGLISH -> "Digital Tasbih"
        AppLanguage.FRENCH -> "Tasbih Numérique"
        AppLanguage.ARABIC -> "المسبحة الإلكترونية"
        AppLanguage.SPANISH -> "Tasbih Digital"
    }

    fun tasbihDesc(lang: AppLanguage): String = when (lang) {
        AppLanguage.PORTUGUESE -> "Contador tátil de Dhikr com vibração ao atingir o ciclo, 9 presets proféticos e histórico de contagens."
        AppLanguage.ENGLISH -> "Haptic Dhikr counter with target reached vibration pattern, 9 prophetic presets and count history."
        AppLanguage.FRENCH -> "Compteur de Dhikr tactile avec vibration de fin de cycle, 9 invocations prophétiques et historique."
        AppLanguage.ARABIC -> "مسبحة إلكترونية بنبضات اهتزازية عند إتمام الدورة، مع ٩ أذكار نبوية وسجل للعبادة."
        AppLanguage.SPANISH -> "Contador táctil de Dhikr con vibración de fin de ciclo, 9 fórmulas proféticas e historial de conteo."
    }

    fun tasbihBadge(lang: AppLanguage): String = when (lang) {
        AppLanguage.PORTUGUESE -> "Contador Tátil"
        AppLanguage.ENGLISH -> "Haptic Counter"
        AppLanguage.FRENCH -> "Compteur Tactile"
        AppLanguage.ARABIC -> "عداد لمسي"
        AppLanguage.SPANISH -> "Contador Táctil"
    }

    fun eventsTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.PORTUGUESE -> "Eventos Islâmicos"
        AppLanguage.ENGLISH -> "Islamic Events"
        AppLanguage.FRENCH -> "Événements Islamiques"
        AppLanguage.ARABIC -> "المناسبات الإسلامية"
        AppLanguage.SPANISH -> "Eventos Islámicos"
    }

    fun eventsDesc(lang: AppLanguage): String = when (lang) {
        AppLanguage.PORTUGUESE -> "Calendário Hijri em tempo real, noites sagradas e contagem regressiva para Ramadã, Eid e Laylat al-Qadr."
        AppLanguage.ENGLISH -> "Real-time Hijri calendar, holy nights and countdown for Ramadan, Eid and Laylat al-Qadr."
        AppLanguage.FRENCH -> "Calendrier Hijri en temps réel, nuits sacrées et compte à rebours pour Ramadan, l'Aïd et Laylat al-Qadr."
        AppLanguage.ARABIC -> "التقويم الهجري المباشر، والمناسبات الدينية وليالي العبادة مثل رمضان وعيدي الفطر والأضحى."
        AppLanguage.SPANISH -> "Calendario Hiyri en tiempo real, noches sagradas y cuenta regresiva para Ramadán, Eid y Laylat al-Qadr."
    }

    fun eventsBadge(lang: AppLanguage): String = when (lang) {
        AppLanguage.PORTUGUESE -> "Festividades & Noites"
        AppLanguage.ENGLISH -> "Holidays & Nights"
        AppLanguage.FRENCH -> "Fêtes & Nuits"
        AppLanguage.ARABIC -> "الأعياد والمناسبات"
        AppLanguage.SPANISH -> "Festividades y Noches"
    }

    // --- SETTINGS SCREEN ---
    fun settingsTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.PORTUGUESE -> "Configurações"
        AppLanguage.ENGLISH -> "Settings"
        AppLanguage.FRENCH -> "Paramètres"
        AppLanguage.ARABIC -> "الإعدادات"
        AppLanguage.SPANISH -> "Configuración"
    }

    fun adhanSettingsHeader(lang: AppLanguage): String = when (lang) {
        AppLanguage.PORTUGUESE -> "Alerta e Áudio do Adhan"
        AppLanguage.ENGLISH -> "Adhan Alert & Audio"
        AppLanguage.FRENCH -> "Alerte et Audio de l'Adhan"
        AppLanguage.ARABIC -> "تنبيه وصوت الأذان"
        AppLanguage.SPANISH -> "Alerta y Audio del Adhan"
    }

    fun playAdhanOnTime(lang: AppLanguage): String = when (lang) {
        AppLanguage.PORTUGUESE -> "Tocar Adhan na hora da oração"
        AppLanguage.ENGLISH -> "Play Adhan at prayer time"
        AppLanguage.FRENCH -> "Jouer l'Adhan à l'heure de prière"
        AppLanguage.ARABIC -> "تشغيل صوت الأذان عند دخول وقت الصلاة"
        AppLanguage.SPANISH -> "Reproducir Adhan a la hora de la oración"
    }

    fun playAdhanDesc(lang: AppLanguage): String = when (lang) {
        AppLanguage.PORTUGUESE -> "Emite o chamado à oração automaticamente nas 5 orações diárias"
        AppLanguage.ENGLISH -> "Plays the call to prayer automatically for the 5 daily prayers"
        AppLanguage.FRENCH -> "Joue l'appel à la prière automatiquement pour les 5 prières quotidiennes"
        AppLanguage.ARABIC -> "إصدار نداء الصلاة تلقائياً عند دخول الأوقات الخمسة"
        AppLanguage.SPANISH -> "Emite el llamado a la oración automáticamente en las 5 oraciones diarias"
    }

    fun advanceReminder(lang: AppLanguage): String = when (lang) {
        AppLanguage.PORTUGUESE -> "Aviso Antecipado (Preparação)"
        AppLanguage.ENGLISH -> "Advance Reminder (Preparation)"
        AppLanguage.FRENCH -> "Rappel Anticipé (Préparation)"
        AppLanguage.ARABIC -> "تنبيه مسبق (للاستعداد)"
        AppLanguage.SPANISH -> "Aviso Anticipado (Preparación)"
    }

    fun advanceReminderDesc(lang: AppLanguage, minutes: Int): String = when (lang) {
        AppLanguage.PORTUGUESE -> if (minutes == 0) "Tocar no momento exato da oração" else "Avisar $minutes minutos antes da oração para você se preparar"
        AppLanguage.ENGLISH -> if (minutes == 0) "Play at exact prayer time" else "Remind $minutes min before prayer to prepare"
        AppLanguage.FRENCH -> if (minutes == 0) "Jouer à l'heure exacte" else "Rappeler $minutes min avant pour vous préparer"
        AppLanguage.ARABIC -> if (minutes == 0) "التشغيل عند دخول الوقت بالضبط" else "تنبيه قبل $minutes دقائق من الوقت للاستعداد"
        AppLanguage.SPANISH -> if (minutes == 0) "Tocar en el momento exacto" else "Avisar $minutes min antes de la oración"
    }

    fun appLanguageTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.PORTUGUESE -> "Idioma do Aplicativo"
        AppLanguage.ENGLISH -> "App Language"
        AppLanguage.FRENCH -> "Langue de l'application"
        AppLanguage.ARABIC -> "لغة التطبيق"
        AppLanguage.SPANISH -> "Idioma de la aplicación"
    }

    fun appLanguageDesc(lang: AppLanguage): String = when (lang) {
        AppLanguage.PORTUGUESE -> "Altera o idioma dos textos, menus e traduções do app"
        AppLanguage.ENGLISH -> "Changes the language of texts, menus and app translations"
        AppLanguage.FRENCH -> "Modifie la langue des textes, menus et traductions"
        AppLanguage.ARABIC -> "تغيير لغة النصوص والقوائم والترجمات"
        AppLanguage.SPANISH -> "Cambia el idioma de los textos, menús y traducciones"
    }

    fun testAdhan(lang: AppLanguage): String = when (lang) {
        AppLanguage.PORTUGUESE -> "Ouvir Demo"
        AppLanguage.ENGLISH -> "Play Demo"
        AppLanguage.FRENCH -> "Écouter Démo"
        AppLanguage.ARABIC -> "سماع النموذج"
        AppLanguage.SPANISH -> "Oír Demo"
    }

    fun searchPlaceholder(lang: AppLanguage): String = when (lang) {
        AppLanguage.PORTUGUESE -> "Buscar..."
        AppLanguage.ENGLISH -> "Search..."
        AppLanguage.FRENCH -> "Rechercher..."
        AppLanguage.ARABIC -> "بحث..."
        AppLanguage.SPANISH -> "Buscar..."
    }

    fun searchDuasPlaceholder(lang: AppLanguage): String = when (lang) {
        AppLanguage.PORTUGUESE -> "Buscar entre 126 Duas e Adhkar..."
        AppLanguage.ENGLISH -> "Search across 126 Duas & Adhkar..."
        AppLanguage.FRENCH -> "Rechercher parmi 126 Invocations..."
        AppLanguage.ARABIC -> "البحث في ١٢٦ دعاء وذكراً..."
        AppLanguage.SPANISH -> "Buscar entre 126 Súplicas y Adhkar..."
    }

    fun searchHadithPlaceholder(lang: AppLanguage): String = when (lang) {
        AppLanguage.PORTUGUESE -> "Buscar ensinamentos do Profeta (saw)..."
        AppLanguage.ENGLISH -> "Search prophetic teachings & Hadiths..."
        AppLanguage.FRENCH -> "Rechercher les enseignements du Prophète..."
        AppLanguage.ARABIC -> "البحث في الأحاديث النبوية الشريفة..."
        AppLanguage.SPANISH -> "Buscar enseñanzas del Profeta (saw)..."
    }

    fun searchHadithsPlaceholder(lang: AppLanguage): String = searchHadithPlaceholder(lang)

    fun dailyHadith(lang: AppLanguage): String = when (lang) {
        AppLanguage.PORTUGUESE -> "Hadith do Dia"
        AppLanguage.ENGLISH -> "Hadith of the Day"
        AppLanguage.FRENCH -> "Hadith du Jour"
        AppLanguage.ARABIC -> "حديث اليوم"
        AppLanguage.SPANISH -> "Hadiz del Día"
    }
}

/**
 * Intelligent Dynamic Translation & Localization Engine
 * Automatically translates date names, prayer statuses, Dua/Hadith English outputs,
 * Islamic event names/descriptions, Moon phases and Asma meanings to the active AppLanguage.
 */
object TranslationHelper {

    private val dayMap = mapOf(
        "monday" to mapOf(AppLanguage.PORTUGUESE to "Segunda-feira", AppLanguage.SPANISH to "Lunes", AppLanguage.FRENCH to "Lundi", AppLanguage.ARABIC to "الإثنين", AppLanguage.ENGLISH to "Monday"),
        "tuesday" to mapOf(AppLanguage.PORTUGUESE to "Terça-feira", AppLanguage.SPANISH to "Martes", AppLanguage.FRENCH to "Mardi", AppLanguage.ARABIC to "الثلاثاء", AppLanguage.ENGLISH to "Tuesday"),
        "wednesday" to mapOf(AppLanguage.PORTUGUESE to "Quarta-feira", AppLanguage.SPANISH to "Miércoles", AppLanguage.FRENCH to "Mercredi", AppLanguage.ARABIC to "الأربعاء", AppLanguage.ENGLISH to "Wednesday"),
        "thursday" to mapOf(AppLanguage.PORTUGUESE to "Quinta-feira", AppLanguage.SPANISH to "Jueves", AppLanguage.FRENCH to "Jeudi", AppLanguage.ARABIC to "الخميس", AppLanguage.ENGLISH to "Thursday"),
        "friday" to mapOf(AppLanguage.PORTUGUESE to "Sexta-feira", AppLanguage.SPANISH to "Viernes", AppLanguage.FRENCH to "Vendredi", AppLanguage.ARABIC to "الجمعة", AppLanguage.ENGLISH to "Friday"),
        "saturday" to mapOf(AppLanguage.PORTUGUESE to "Sábado", AppLanguage.SPANISH to "Sábado", AppLanguage.FRENCH to "Samedi", AppLanguage.ARABIC to "السبت", AppLanguage.ENGLISH to "Saturday"),
        "sunday" to mapOf(AppLanguage.PORTUGUESE to "Domingo", AppLanguage.SPANISH to "Domingo", AppLanguage.FRENCH to "Dimanche", AppLanguage.ARABIC to "الأحد", AppLanguage.ENGLISH to "Sunday")
    )

    private val monthMap = mapOf(
        "january" to mapOf(AppLanguage.PORTUGUESE to "Janeiro", AppLanguage.SPANISH to "Enero", AppLanguage.FRENCH to "Janvier", AppLanguage.ARABIC to "يناير", AppLanguage.ENGLISH to "January"),
        "february" to mapOf(AppLanguage.PORTUGUESE to "Fevereiro", AppLanguage.SPANISH to "Febrero", AppLanguage.FRENCH to "Février", AppLanguage.ARABIC to "فبراير", AppLanguage.ENGLISH to "February"),
        "march" to mapOf(AppLanguage.PORTUGUESE to "Março", AppLanguage.SPANISH to "Marzo", AppLanguage.FRENCH to "Mars", AppLanguage.ARABIC to "مارس", AppLanguage.ENGLISH to "March"),
        "april" to mapOf(AppLanguage.PORTUGUESE to "Abril", AppLanguage.SPANISH to "Abril", AppLanguage.FRENCH to "Avril", AppLanguage.ARABIC to "أبريل", AppLanguage.ENGLISH to "April"),
        "may" to mapOf(AppLanguage.PORTUGUESE to "Maio", AppLanguage.SPANISH to "Mayo", AppLanguage.FRENCH to "Mai", AppLanguage.ARABIC to "مايو", AppLanguage.ENGLISH to "May"),
        "june" to mapOf(AppLanguage.PORTUGUESE to "Junho", AppLanguage.SPANISH to "Junio", AppLanguage.FRENCH to "Juin", AppLanguage.ARABIC to "يونيو", AppLanguage.ENGLISH to "June"),
        "july" to mapOf(AppLanguage.PORTUGUESE to "Julho", AppLanguage.SPANISH to "Julio", AppLanguage.FRENCH to "Juillet", AppLanguage.ARABIC to "يوليو", AppLanguage.ENGLISH to "July"),
        "august" to mapOf(AppLanguage.PORTUGUESE to "Agosto", AppLanguage.SPANISH to "Agosto", AppLanguage.FRENCH to "Août", AppLanguage.ARABIC to "أغسطس", AppLanguage.ENGLISH to "August"),
        "september" to mapOf(AppLanguage.PORTUGUESE to "Setembro", AppLanguage.SPANISH to "Septiembre", AppLanguage.FRENCH to "Septembre", AppLanguage.ARABIC to "سبتمبر", AppLanguage.ENGLISH to "September"),
        "october" to mapOf(AppLanguage.PORTUGUESE to "Outubro", AppLanguage.SPANISH to "Octubre", AppLanguage.FRENCH to "Octobre", AppLanguage.ARABIC to "أكتوبر", AppLanguage.ENGLISH to "October"),
        "november" to mapOf(AppLanguage.PORTUGUESE to "Novembro", AppLanguage.SPANISH to "Noviembre", AppLanguage.FRENCH to "Novembre", AppLanguage.ARABIC to "نوفمبر", AppLanguage.ENGLISH to "November"),
        "december" to mapOf(AppLanguage.PORTUGUESE to "Dezembro", AppLanguage.SPANISH to "Diciembre", AppLanguage.FRENCH to "Décembre", AppLanguage.ARABIC to "ديسمبر", AppLanguage.ENGLISH to "December")
    )

    fun translateCategory(cat: String?, lang: AppLanguage): String {
        if (cat.isNullOrBlank()) return ""
        return when (cat.lowercase()) {
            "all" -> when (lang) {
                AppLanguage.PORTUGUESE -> "Todas"
                AppLanguage.ENGLISH -> "All"
                AppLanguage.FRENCH -> "Toutes"
                AppLanguage.ARABIC -> "الكل"
                AppLanguage.SPANISH -> "Todas"
            }
            "morning", "morning_evening", "morning_and_evening" -> when (lang) {
                AppLanguage.PORTUGUESE -> "Manhã e Noite"
                AppLanguage.ENGLISH -> "Morning & Evening"
                AppLanguage.FRENCH -> "Matin & Soir"
                AppLanguage.ARABIC -> "أذكار الصباح والمساء"
                AppLanguage.SPANISH -> "Mañana y Tarde"
            }
            "prayer", "salah" -> when (lang) {
                AppLanguage.PORTUGUESE -> "Oração (Salah)"
                AppLanguage.ENGLISH -> "Prayer (Salah)"
                AppLanguage.FRENCH -> "Prière (Salat)"
                AppLanguage.ARABIC -> "الصلاة"
                AppLanguage.SPANISH -> "Oración (Salah)"
            }
            "forgiveness", "istighfar" -> when (lang) {
                AppLanguage.PORTUGUESE -> "Perdão e Arrependimento"
                AppLanguage.ENGLISH -> "Forgiveness (Istighfar)"
                AppLanguage.FRENCH -> "Pardon et Repentir"
                AppLanguage.ARABIC -> "الاستغفار والتوبة"
                AppLanguage.SPANISH -> "Perdón y Arrepentimiento"
            }
            "protection" -> when (lang) {
                AppLanguage.PORTUGUESE -> "Proteção e Refúgio"
                AppLanguage.ENGLISH -> "Protection & Refuge"
                AppLanguage.FRENCH -> "Protection et Refuge"
                AppLanguage.ARABIC -> "الحفظ والوقاية"
                AppLanguage.SPANISH -> "Protección y Refugio"
            }
            "guidance" -> when (lang) {
                AppLanguage.PORTUGUESE -> "Orientação e Sabedoria"
                AppLanguage.ENGLISH -> "Guidance & Wisdom"
                AppLanguage.FRENCH -> "Guidance et Sagesse"
                AppLanguage.ARABIC -> "الهداية والتوفيق"
                AppLanguage.SPANISH -> "Guía y Sabiduría"
            }
            "family" -> when (lang) {
                AppLanguage.PORTUGUESE -> "Família e Pais"
                AppLanguage.ENGLISH -> "Family & Parents"
                AppLanguage.FRENCH -> "Famille et Parents"
                AppLanguage.ARABIC -> "الأسرة والوالدين"
                AppLanguage.SPANISH -> "Familia y Padres"
            }
            "daily", "daily_life" -> when (lang) {
                AppLanguage.PORTUGUESE -> "Vida Diária"
                AppLanguage.ENGLISH -> "Daily Life"
                AppLanguage.FRENCH -> "Vie Quotidienne"
                AppLanguage.ARABIC -> "الحياة اليومية"
                AppLanguage.SPANISH -> "Vida Diaria"
            }
            "quranic" -> when (lang) {
                AppLanguage.PORTUGUESE -> "Duas do Alcorão"
                AppLanguage.ENGLISH -> "Quranic Duas"
                AppLanguage.FRENCH -> "Invocations Coraniques"
                AppLanguage.ARABIC -> "أدعية قرآنية"
                AppLanguage.SPANISH -> "Súplicas Coránicas"
            }
            "praise" -> when (lang) {
                AppLanguage.PORTUGUESE -> "Louvor e Gratidão"
                AppLanguage.ENGLISH -> "Praise & Gratitude"
                AppLanguage.FRENCH -> "Louange et Gratitude"
                AppLanguage.ARABIC -> "الحمد والثناء"
                AppLanguage.SPANISH -> "Alabanza y Gratitud"
            }
            else -> cat.replace("_", " ").replaceFirstChar { it.uppercase() }
        }
    }

    fun translatePrayerName(name: String, lang: AppLanguage): String = when (name.lowercase()) {
        "fajr" -> when (lang) {
            AppLanguage.PORTUGUESE -> "Fajr"
            AppLanguage.SPANISH -> "Fayr"
            AppLanguage.FRENCH -> "Fajr"
            AppLanguage.ARABIC -> "الفجر"
            AppLanguage.ENGLISH -> "Fajr"
        }
        "sunrise", "shuruq", "nascer" -> when (lang) {
            AppLanguage.PORTUGUESE -> "Nascer do Sol"
            AppLanguage.SPANISH -> "Amanecer"
            AppLanguage.FRENCH -> "Lever du soleil"
            AppLanguage.ARABIC -> "الشروق"
            AppLanguage.ENGLISH -> "Sunrise"
        }
        "dhuhr", "zuhr" -> when (lang) {
            AppLanguage.PORTUGUESE -> "Dhuhr"
            AppLanguage.SPANISH -> "Dhuhr"
            AppLanguage.FRENCH -> "Dhuhr"
            AppLanguage.ARABIC -> "الظهر"
            AppLanguage.ENGLISH -> "Dhuhr"
        }
        "asr" -> when (lang) {
            AppLanguage.PORTUGUESE -> "Asr"
            AppLanguage.SPANISH -> "Asr"
            AppLanguage.FRENCH -> "Asr"
            AppLanguage.ARABIC -> "العصر"
            AppLanguage.ENGLISH -> "Asr"
        }
        "maghrib" -> when (lang) {
            AppLanguage.PORTUGUESE -> "Maghrib"
            AppLanguage.SPANISH -> "Magrib"
            AppLanguage.FRENCH -> "Maghrib"
            AppLanguage.ARABIC -> "المغرب"
            AppLanguage.ENGLISH -> "Maghrib"
        }
        "isha" -> when (lang) {
            AppLanguage.PORTUGUESE -> "Isha"
            AppLanguage.SPANISH -> "Isha"
            AppLanguage.FRENCH -> "Isha"
            AppLanguage.ARABIC -> "العشاء"
            AppLanguage.ENGLISH -> "Isha"
        }
        "imsak" -> when (lang) {
            AppLanguage.PORTUGUESE -> "Imsak"
            AppLanguage.SPANISH -> "Imsak"
            AppLanguage.FRENCH -> "Imsak"
            AppLanguage.ARABIC -> "الإمساك"
            AppLanguage.ENGLISH -> "Imsak"
        }
        else -> name
    }

    fun translatePrayerSubname(id: String, lang: AppLanguage): String = when (id.lowercase()) {
        "imsak" -> when (lang) {
            AppLanguage.PORTUGUESE -> "Imsak (Fim do Suhoor)"
            AppLanguage.ENGLISH -> "Imsak (Suhoor End)"
            AppLanguage.FRENCH -> "Imsak (Fin du Sahur)"
            AppLanguage.ARABIC -> "الإمساك (نهاية السحور)"
            AppLanguage.SPANISH -> "Imsak (Fin del Suhur)"
        }
        "fajr" -> when (lang) {
            AppLanguage.PORTUGUESE -> "Fajr (Oração da Alvorada)"
            AppLanguage.ENGLISH -> "Fajr (Dawn Prayer)"
            AppLanguage.FRENCH -> "Fajr (Prière de l'Aube)"
            AppLanguage.ARABIC -> "صلاة الفجر"
            AppLanguage.SPANISH -> "Fajr (Oración del Alba)"
        }
        "sunrise" -> when (lang) {
            AppLanguage.PORTUGUESE -> "Nascer do Sol (Shuruq)"
            AppLanguage.ENGLISH -> "Sunrise (Shuruq)"
            AppLanguage.FRENCH -> "Lever du soleil (Chourouq)"
            AppLanguage.ARABIC -> "شروق الشمس"
            AppLanguage.SPANISH -> "Amanecer (Shuruq)"
        }
        "dhuhr" -> when (lang) {
            AppLanguage.PORTUGUESE -> "Dhuhr (Oração do Meio-Dia)"
            AppLanguage.ENGLISH -> "Dhuhr (Noon Prayer)"
            AppLanguage.FRENCH -> "Dhuhr (Prière de Midi)"
            AppLanguage.ARABIC -> "صلاة الظهر"
            AppLanguage.SPANISH -> "Dhuhr (Oración del Mediodía)"
        }
        "asr" -> when (lang) {
            AppLanguage.PORTUGUESE -> "Asr (Oração da Tarde)"
            AppLanguage.ENGLISH -> "Asr (Afternoon Prayer)"
            AppLanguage.FRENCH -> "Asr (Prière de l'Après-midi)"
            AppLanguage.ARABIC -> "صلاة العصر"
            AppLanguage.SPANISH -> "Asr (Oración de la Tarde)"
        }
        "maghrib" -> when (lang) {
            AppLanguage.PORTUGUESE -> "Maghrib (Oração do Pôr do Sol)"
            AppLanguage.ENGLISH -> "Maghrib (Sunset Prayer)"
            AppLanguage.FRENCH -> "Maghrib (Prière du Coucher)"
            AppLanguage.ARABIC -> "صلاة المغرب"
            AppLanguage.SPANISH -> "Maghrib (Oración del Ocaso)"
        }
        "isha" -> when (lang) {
            AppLanguage.PORTUGUESE -> "Isha (Oração da Noite)"
            AppLanguage.ENGLISH -> "Isha (Night Prayer)"
            AppLanguage.FRENCH -> "Isha (Prière de la Nuit)"
            AppLanguage.ARABIC -> "صلاة العشاء"
            AppLanguage.SPANISH -> "Isha (Oración de la Noche)"
        }
        else -> id
    }

    fun translateDuaCategory(categoryId: String, originalName: String, lang: AppLanguage): String = when (categoryId.lowercase()) {
        "all" -> when (lang) {
            AppLanguage.PORTUGUESE -> "Todas as Categorias"
            AppLanguage.ENGLISH -> "All Categories"
            AppLanguage.FRENCH -> "Toutes les Catégories"
            AppLanguage.ARABIC -> "جميع الفئات"
            AppLanguage.SPANISH -> "Todas las Categorías"
        }
        "morning", "morning_evening" -> when (lang) {
            AppLanguage.PORTUGUESE -> "Manhã & Noite"
            AppLanguage.ENGLISH -> "Morning & Evening"
            AppLanguage.FRENCH -> "Matin & Soir"
            AppLanguage.ARABIC -> "أذكار الصباح والمساء"
            AppLanguage.SPANISH -> "Mañana y Noche"
        }
        "prayer", "salah" -> when (lang) {
            AppLanguage.PORTUGUESE -> "Oração & Salah"
            AppLanguage.ENGLISH -> "Prayer & Salah"
            AppLanguage.FRENCH -> "Prière & Salat"
            AppLanguage.ARABIC -> "الصلاة والأذان"
            AppLanguage.SPANISH -> "Oración y Salah"
        }
        "forgiveness", "istighfar" -> when (lang) {
            AppLanguage.PORTUGUESE -> "Perdão & Arrependimento"
            AppLanguage.ENGLISH -> "Forgiveness & Repentance"
            AppLanguage.FRENCH -> "Pardon & Repentir"
            AppLanguage.ARABIC -> "الاستغفار والتوبة"
            AppLanguage.SPANISH -> "Perdón y Arrepentimiento"
        }
        "protection" -> when (lang) {
            AppLanguage.PORTUGUESE -> "Proteção & Refúgio"
            AppLanguage.ENGLISH -> "Protection & Refuge"
            AppLanguage.FRENCH -> "Protection & Refuge"
            AppLanguage.ARABIC -> "الحفظ والاستعاذة"
            AppLanguage.SPANISH -> "Protección y Refugio"
        }
        "food", "food_drink" -> when (lang) {
            AppLanguage.PORTUGUESE -> "Alimentação & Refeições"
            AppLanguage.ENGLISH -> "Food & Drink"
            AppLanguage.FRENCH -> "Nourriture & Boisson"
            AppLanguage.ARABIC -> "الطعام والشراب"
            AppLanguage.SPANISH -> "Alimentos y Bebidas"
        }
        "travel" -> when (lang) {
            AppLanguage.PORTUGUESE -> "Viagem & Transporte"
            AppLanguage.ENGLISH -> "Travel & Journeys"
            AppLanguage.FRENCH -> "Voyage & Déplacement"
            AppLanguage.ARABIC -> "دعاء السفر"
            AppLanguage.SPANISH -> "Viajes y Trayectos"
        }
        "health", "healing" -> when (lang) {
            AppLanguage.PORTUGUESE -> "Saúde & Cura (Shifa)"
            AppLanguage.ENGLISH -> "Health & Healing"
            AppLanguage.FRENCH -> "Santé & Guérison"
            AppLanguage.ARABIC -> "الشفاء والعافية"
            AppLanguage.SPANISH -> "Salud y Curación"
        }
        "family", "home" -> when (lang) {
            AppLanguage.PORTUGUESE -> "Família & Lar"
            AppLanguage.ENGLISH -> "Family & Home"
            AppLanguage.FRENCH -> "Famille & Foyer"
            AppLanguage.ARABIC -> "الأسرة والبيت"
            AppLanguage.SPANISH -> "Familia y Hogar"
        }
        "patience", "gratitude" -> when (lang) {
            AppLanguage.PORTUGUESE -> "Paciência & Gratidão"
            AppLanguage.ENGLISH -> "Patience & Gratitude"
            AppLanguage.FRENCH -> "Patience & Gratitude"
            AppLanguage.ARABIC -> "الصبر والشكر"
            AppLanguage.SPANISH -> "Paciencia y Gratitud"
        }
        "guidance", "knowledge" -> when (lang) {
            AppLanguage.PORTUGUESE -> "Conhecimento & Orientação"
            AppLanguage.ENGLISH -> "Guidance & Knowledge"
            AppLanguage.FRENCH -> "Guidance & Savoir"
            AppLanguage.ARABIC -> "العلم والهداية"
            AppLanguage.SPANISH -> "Conocimiento y Guía"
        }
        "daily", "daily_life" -> when (lang) {
            AppLanguage.PORTUGUESE -> "Vida Diária"
            AppLanguage.ENGLISH -> "Daily Life"
            AppLanguage.FRENCH -> "Vie Quotidienne"
            AppLanguage.ARABIC -> "الحياة اليومية"
            AppLanguage.SPANISH -> "Vida Cotidiana"
        }
        "ramadan", "fasting" -> when (lang) {
            AppLanguage.PORTUGUESE -> "Ramadã & Jejum"
            AppLanguage.ENGLISH -> "Ramadan & Fasting"
            AppLanguage.FRENCH -> "Ramadan & Jeûne"
            AppLanguage.ARABIC -> "رمضان والصيام"
            AppLanguage.SPANISH -> "Ramadán y Ayuno"
        }
        "distress", "hardship" -> when (lang) {
            AppLanguage.PORTUGUESE -> "Angústia & Dificuldades"
            AppLanguage.ENGLISH -> "Distress & Hardship"
            AppLanguage.FRENCH -> "Détresse & Épreuves"
            AppLanguage.ARABIC -> "الكرب والشدائد"
            AppLanguage.SPANISH -> "Angustia y Dificultad"
        }
        else -> originalName
    }

    /**
     * Translates English Hadith / Dua texts or notes dynamically into target language
     */
    fun translateContentText(englishText: String?, lang: AppLanguage): String {
        if (englishText.isNullOrBlank() || lang == AppLanguage.ENGLISH) return englishText ?: ""

        var translated: String = englishText

        // Handle common Islamic English expressions
        val replacements = when (lang) {
            AppLanguage.PORTUGUESE -> listOf(
                "Narrated " to "Narrado por ",
                "I heard Allah's Messenger" to "Ouvi o Mensageiro de Allah",
                "The Prophet (ﷺ) said:" to "O Profeta (ﷺ) disse:",
                "The Prophet said:" to "O Profeta disse:",
                "Messenger of Allah (ﷺ) said:" to "O Mensageiro de Allah (ﷺ) disse:",
                "The reward of deeds depends upon the intentions" to "A recompensa das ações depende das intenções",
                "and every person will get the reward according to what he has intended." to "e cada pessoa receberá a recompensa de acordo com o que pretendeu.",
                "None of you truly believes until he loves for his brother what he loves for himself." to "Nenhum de vós crê verdadeiramente até que deseje para seu irmão o que deseja para si mesmo.",
                "The best among you are those who have the best manners and character." to "Os melhores entre vós são aqueles que possuem o melhor caráter e comportamento.",
                "Whoever believes in Allah and the Last Day should speak good or remain silent." to "Quem crê em Allah e no Último Dia, que fale o bem ou permaneça em silêncio.",
                "Take advantage of five before five:" to "Aproveita cinco coisas antes de cinco:",
                "your youth before your old age," to "a tua juventude antes da velhice,",
                "your health before your illness," to "a tua saúde antes da doença,",
                "your wealth before your poverty," to "a tua riqueza antes da pobreza,",
                "your free time before your work," to "o teu tempo livre antes da ocupação,",
                "and your life before your death." to "e a tua vida antes da morte.",
                "Allah does not look at your forms or your wealth," to "Allah não olha para as vossas aparências nem para as vossas riquezas,",
                "but He looks at your hearts and your deeds." to "mas olha para os vossos corações e para as vossas ações.",
                "O Allah, You are my Lord." to "Ó Allah, Tu és o meu Senhor.",
                "There is no deity except You." to "Não há divindade exceto Tu.",
                "You created me and I am Your servant." to "Tu me criaste e eu sou Teu servo.",
                "Praise be to Allah" to "Louvado seja Allah",
                "Glory be to Allah" to "Glória a Allah",
                "In the name of Allah" to "Em nome de Allah"
            )
            AppLanguage.SPANISH -> listOf(
                "Narrated " to "Narrado por ",
                "I heard Allah's Messenger" to "Escuché al Mensajero de Alá",
                "The Prophet (ﷺ) said:" to "El Profeta (ﷺ) dijo:",
                "The Prophet said:" to "El Profeta dijo:",
                "The reward of deeds depends upon the intentions" to "La recompensa de las obras depende de las intenciones",
                "and every person will get the reward according to what he has intended." to "y cada persona obtendrá la recompensa según lo que haya tenido intención.",
                "None of you truly believes until he loves for his brother what he loves for himself." to "Ninguno de vosotros cree verdaderamente hasta que ama para su hermano lo que ama para sí mismo.",
                "The best among you are those who have the best manners and character." to "Los mejores de entre vosotros son los que tienen el mejor carácter y conducta.",
                "Whoever believes in Allah and the Last Day should speak good or remain silent." to "Quien crea en Alá y en el Último Día, que diga cosas buenas o guarde silencio.",
                "O Allah, You are my Lord." to "Oh Alá, Tú eres mi Señor.",
                "There is no deity except You." to "No hay más divinidad que Tú."
            )
            AppLanguage.FRENCH -> listOf(
                "Narrated " to "Rapporté par ",
                "I heard Allah's Messenger" to "J'ai entendu le Messager d'Allah",
                "The Prophet (ﷺ) said:" to "Le Prophète (ﷺ) a dit :",
                "The Prophet said:" to "Le Prophète a dit :",
                "The reward of deeds depends upon the intentions" to "La récompense des actes dépend des intentions",
                "and every person will get the reward according to what he has intended." to "et chaque personne sera rétribuée selon son intention.",
                "None of you truly believes until he loves for his brother what he loves for himself." to "Aucun d'entre vous ne sera véritablement croyant tant qu'il n'aimera pas pour son frère ce qu'il aime pour lui-même.",
                "The best among you are those who have the best manners and character." to "Les meilleurs d'entre vous sont ceux qui ont le meilleur comportement.",
                "Whoever believes in Allah and the Last Day should speak good or remain silent." to "Que celui qui croit en Allah et au Jour Dernier dise du bien ou garde le silence.",
                "O Allah, You are my Lord." to "Ô Allah, Tu es mon Seigneur.",
                "There is no deity except You." to "Il n'y a de divinité que Toi."
            )
            AppLanguage.ARABIC -> listOf(
                "Narrated " to "عن ",
                "The Prophet (ﷺ) said:" to "قال النبي ﷺ:",
                "The Prophet said:" to "قال النبي ﷺ:"
            )
            AppLanguage.ENGLISH -> emptyList()
        }

        for ((en, tr) in replacements) {
            translated = translated.replace(en, tr)
        }

        return translated
    }

    fun translateMoonPhase(phase: String?, lang: AppLanguage): String = when (phase?.lowercase()) {
        "new moon" -> when (lang) {
            AppLanguage.PORTUGUESE -> "Lua Nova (Mahq)"
            AppLanguage.ENGLISH -> "New Moon"
            AppLanguage.FRENCH -> "Nouvelle Lune"
            AppLanguage.ARABIC -> "المحاق / ولادة الهلال"
            AppLanguage.SPANISH -> "Luna Nueva"
        }
        "waxing crescent" -> when (lang) {
            AppLanguage.PORTUGUESE -> "Crescente Inicial (Hilal)"
            AppLanguage.ENGLISH -> "Waxing Crescent (Hilal)"
            AppLanguage.FRENCH -> "Premier Croissant (Hilal)"
            AppLanguage.ARABIC -> "هلال متزايد"
            AppLanguage.SPANISH -> "Creciente Inicial (Hilal)"
        }
        "first quarter" -> when (lang) {
            AppLanguage.PORTUGUESE -> "Quarto Crescente"
            AppLanguage.ENGLISH -> "First Quarter"
            AppLanguage.FRENCH -> "Premier Quartier"
            AppLanguage.ARABIC -> "التربيع الأول"
            AppLanguage.SPANISH -> "Cuarto Creciente"
        }
        "waxing gibbous" -> when (lang) {
            AppLanguage.PORTUGUESE -> "Gibosa Crescente"
            AppLanguage.ENGLISH -> "Waxing Gibbous"
            AppLanguage.FRENCH -> "Gibbeuse Croissante"
            AppLanguage.ARABIC -> "أحدب متزايد"
            AppLanguage.SPANISH -> "Gibosa Creciente"
        }
        "full moon" -> when (lang) {
            AppLanguage.PORTUGUESE -> "Lua Cheia (Badr)"
            AppLanguage.ENGLISH -> "Full Moon (Badr)"
            AppLanguage.FRENCH -> "Pleine Lune (Badr)"
            AppLanguage.ARABIC -> "بدر كامل"
            AppLanguage.SPANISH -> "Luna Llena (Badr)"
        }
        "waning gibbous" -> when (lang) {
            AppLanguage.PORTUGUESE -> "Gibosa Minguante"
            AppLanguage.ENGLISH -> "Waning Gibbous"
            AppLanguage.FRENCH -> "Gibbeuse Décroissante"
            AppLanguage.ARABIC -> "أحدب متناقص"
            AppLanguage.SPANISH -> "Gibosa Menguante"
        }
        "last quarter" -> when (lang) {
            AppLanguage.PORTUGUESE -> "Quarto Minguante"
            AppLanguage.ENGLISH -> "Last Quarter"
            AppLanguage.FRENCH -> "Dernier Quartier"
            AppLanguage.ARABIC -> "التربيع الأخير"
            AppLanguage.SPANISH -> "Cuarto Menguante"
        }
        "waning crescent" -> when (lang) {
            AppLanguage.PORTUGUESE -> "Crescente Minguante"
            AppLanguage.ENGLISH -> "Waning Crescent"
            AppLanguage.FRENCH -> "Dernier Croissant"
            AppLanguage.ARABIC -> "هلال متناقص"
            AppLanguage.SPANISH -> "Creciente Menguante"
        }
        else -> phase ?: ""
    }

    fun translateMoonVisibility(visibility: String?, lang: AppLanguage): String = when (visibility?.lowercase()) {
        "easily_visible", "easily visible" -> when (lang) {
            AppLanguage.PORTUGUESE -> "Facilmente Visível a Olho Nu"
            AppLanguage.ENGLISH -> "Easily Visible to Naked Eye"
            AppLanguage.FRENCH -> "Facilement Visible à l'Œil Nu"
            AppLanguage.ARABIC -> "رؤية واضحة بالعين المجردة"
            AppLanguage.SPANISH -> "Fácilmente Visible a Simple Vista"
        }
        "possibly_visible", "possibly visible", "visible_under_perfect_conditions" -> when (lang) {
            AppLanguage.PORTUGUESE -> "Visível sob Condições Favoráveis"
            AppLanguage.ENGLISH -> "Visible Under Perfect Conditions"
            AppLanguage.FRENCH -> "Visible sous Conditions Idéales"
            AppLanguage.ARABIC -> "ممكن الرؤية في ظروف جوية مثالية"
            AppLanguage.SPANISH -> "Visible bajo Condiciones Favorables"
        }
        "need_telescope", "need_optical_aid" -> when (lang) {
            AppLanguage.PORTUGUESE -> "Requer Telescópio ou Binóculos"
            AppLanguage.ENGLISH -> "Requires Telescope or Binoculars"
            AppLanguage.FRENCH -> "Nécessite Télescope ou Jumelles"
            AppLanguage.ARABIC -> "تتطلب منظاراً أو تليسكوباً"
            AppLanguage.SPANISH -> "Requiere Telescopio o Prismáticos"
        }
        "not_visible", "invisible" -> when (lang) {
            AppLanguage.PORTUGUESE -> "Não Visível no Horizonte"
            AppLanguage.ENGLISH -> "Not Visible on Horizon"
            AppLanguage.FRENCH -> "Non Visible à l'Horizon"
            AppLanguage.ARABIC -> "غير مرئي في الأفق"
            AppLanguage.SPANISH -> "No Visible en el Horizonte"
        }
        else -> visibility ?: ""
    }

    // --- DUA TRANSLATIONS ---
    fun translateDuaTitle(title: String, lang: AppLanguage): String {
        if (lang == AppLanguage.ENGLISH) return title
        val lower = title.lowercase()
        return when {
            lower.contains("good morning") || lower.contains("morning") -> when (lang) {
                AppLanguage.PORTUGUESE -> "Dua da Manhã"
                AppLanguage.SPANISH -> "Súplica de la Mañana"
                AppLanguage.FRENCH -> "Invocation du Matin"
                AppLanguage.ARABIC -> "دعاء الصباح"
                else -> title
            }
            lower.contains("evening") -> when (lang) {
                AppLanguage.PORTUGUESE -> "Dua da Tarde/Noite"
                AppLanguage.SPANISH -> "Súplica de la Tarde/Noche"
                AppLanguage.FRENCH -> "Invocation du Soir"
                AppLanguage.ARABIC -> "دعاء المساء"
                else -> title
            }
            lower.contains("sleeping") || lower.contains("sleep") || lower.contains("bed") -> when (lang) {
                AppLanguage.PORTUGUESE -> "Dua antes de Dormir"
                AppLanguage.SPANISH -> "Súplica antes de Dormir"
                AppLanguage.FRENCH -> "Invocation avant de Dormir"
                AppLanguage.ARABIC -> "دعاء النوم"
                else -> title
            }
            lower.contains("waking") || lower.contains("wake") -> when (lang) {
                AppLanguage.PORTUGUESE -> "Dua ao Acordar"
                AppLanguage.SPANISH -> "Súplica al Despertar"
                AppLanguage.FRENCH -> "Invocation au Réveil"
                AppLanguage.ARABIC -> "دعاء الاستيقاظ"
                else -> title
            }
            lower.contains("eating") || lower.contains("meal") || lower.contains("food") -> when (lang) {
                AppLanguage.PORTUGUESE -> "Dua para as Refeições"
                AppLanguage.SPANISH -> "Súplica para la Comida"
                AppLanguage.FRENCH -> "Invocation pour le Repas"
                AppLanguage.ARABIC -> "دعاء الطعام"
                else -> title
            }
            lower.contains("travel") || lower.contains("journey") -> when (lang) {
                AppLanguage.PORTUGUESE -> "Dua de Viagem"
                AppLanguage.SPANISH -> "Súplica de Viaje"
                AppLanguage.FRENCH -> "Invocation du Voyage"
                AppLanguage.ARABIC -> "دعاء السفر"
                else -> title
            }
            lower.contains("anxiety") || lower.contains("distress") || lower.contains("sorrow") || lower.contains("worry") -> when (lang) {
                AppLanguage.PORTUGUESE -> "Dua contra Ansiedade e Angústia"
                AppLanguage.SPANISH -> "Súplica contra la Ansiedad y Angustia"
                AppLanguage.FRENCH -> "Invocation contre l'Angoisse et la Tristesse"
                AppLanguage.ARABIC -> "دعاء تفريج الهم والحزن"
                else -> title
            }
            lower.contains("forgiveness") || lower.contains("istighfar") || lower.contains("repent") -> when (lang) {
                AppLanguage.PORTUGUESE -> "Dua de Perdão (Istighfar)"
                AppLanguage.SPANISH -> "Súplica de Perdón (Istighfar)"
                AppLanguage.FRENCH -> "Invocation de Pardon (Istighfar)"
                AppLanguage.ARABIC -> "سيد الاستغفار وطلب المغفرة"
                else -> title
            }
            lower.contains("protection") || lower.contains("evil") || lower.contains("shelter") -> when (lang) {
                AppLanguage.PORTUGUESE -> "Dua de Proteção e Refúgio"
                AppLanguage.SPANISH -> "Súplica de Protección y Refugio"
                AppLanguage.FRENCH -> "Invocation de Protection"
                AppLanguage.ARABIC -> "دعاء الحفظ والتحصين"
                else -> title
            }
            lower.contains("home") || lower.contains("house") -> when (lang) {
                AppLanguage.PORTUGUESE -> "Dua ao Entrar/Sair de Casa"
                AppLanguage.SPANISH -> "Súplica al Entrar/Salir de Casa"
                AppLanguage.FRENCH -> "Invocation de la Maison"
                AppLanguage.ARABIC -> "دعاء دخول وخروج المنزل"
                else -> title
            }
            lower.contains("mosque") || lower.contains("masjid") -> when (lang) {
                AppLanguage.PORTUGUESE -> "Dua da Mesquita"
                AppLanguage.SPANISH -> "Súplica de la Mezquita"
                AppLanguage.FRENCH -> "Invocation de la Mosquée"
                AppLanguage.ARABIC -> "دعاء المسجد"
                else -> title
            }
            lower.contains("parents") || lower.contains("family") -> when (lang) {
                AppLanguage.PORTUGUESE -> "Dua pelos Pais e Família"
                AppLanguage.SPANISH -> "Súplica por los Padres"
                AppLanguage.FRENCH -> "Invocation pour les Parents"
                AppLanguage.ARABIC -> "دعاء للوالدين"
                else -> title
            }
            lower.contains("knowledge") -> when (lang) {
                AppLanguage.PORTUGUESE -> "Dua pelo Aumento do Conhecimento"
                AppLanguage.SPANISH -> "Súplica por Conocimiento"
                AppLanguage.FRENCH -> "Invocation pour la Connaissance"
                AppLanguage.ARABIC -> "دعاء زيادة العلم"
                else -> title
            }
            else -> translateGeneralPhrase(title, lang)
        }
    }

    fun translateDuaTranslation(text: String, lang: AppLanguage): String {
        if (lang == AppLanguage.ENGLISH || text.isBlank()) return text
        return translateGeneralPhrase(text, lang)
    }

    // --- HADITH TRANSLATIONS ---
    fun translateHadithEnglish(text: String, lang: AppLanguage): String {
        if (lang == AppLanguage.ENGLISH || text.isBlank()) return text
        return translateGeneralPhrase(text, lang)
    }

    // --- TAFSIR TRANSLATIONS ---
    fun translateTafsirText(text: String, lang: AppLanguage): String {
        if (lang == AppLanguage.ENGLISH || text.isBlank()) return text
        return translateGeneralPhrase(text, lang)
    }

    // --- 99 NAMES TRANSLATIONS ---
    fun translateAsmaEnglish(english: String, lang: AppLanguage): String {
        if (lang == AppLanguage.ENGLISH) return english
        return when (english.lowercase().trim()) {
            "the most merciful", "the all-merciful" -> when (lang) {
                AppLanguage.PORTUGUESE -> "O Mais Misericordioso"
                AppLanguage.SPANISH -> "El Clementísimo"
                AppLanguage.FRENCH -> "Le Tout Miséricordieux"
                AppLanguage.ARABIC -> "الرَّحْمٰنُ"
                else -> english
            }
            "the especially merciful", "the entirely merciful" -> when (lang) {
                AppLanguage.PORTUGUESE -> "O Especialmente Misericordioso"
                AppLanguage.SPANISH -> "El Misericordioso"
                AppLanguage.FRENCH -> "Le Très Miséricordieux"
                AppLanguage.ARABIC -> "الرَّحِيمُ"
                else -> english
            }
            "the king", "the sovereign" -> when (lang) {
                AppLanguage.PORTUGUESE -> "O Soberano / Rei"
                AppLanguage.SPANISH -> "El Rey Soberano"
                AppLanguage.FRENCH -> "Le Souverain"
                AppLanguage.ARABIC -> "المَلِكُ"
                else -> english
            }
            "the most holy", "the holy" -> when (lang) {
                AppLanguage.PORTUGUESE -> "O Sagrado / Puro"
                AppLanguage.SPANISH -> "El Sagrado"
                AppLanguage.FRENCH -> "Le Pur"
                AppLanguage.ARABIC -> "القُدُّوسُ"
                else -> english
            }
            "the source of peace", "the giver of peace" -> when (lang) {
                AppLanguage.PORTUGUESE -> "A Fonte da Paz"
                AppLanguage.SPANISH -> "La Fuente de Paz"
                AppLanguage.FRENCH -> "La Paix"
                AppLanguage.ARABIC -> "السَّلَامُ"
                else -> english
            }
            "the creator" -> when (lang) {
                AppLanguage.PORTUGUESE -> "O Criador Supremo"
                AppLanguage.SPANISH -> "El Creador"
                AppLanguage.FRENCH -> "Le Créateur"
                AppLanguage.ARABIC -> "الخَالِقُ"
                else -> english
            }
            "the forgiver", "the all-forgiving" -> when (lang) {
                AppLanguage.PORTUGUESE -> "O Perdoador"
                AppLanguage.SPANISH -> "El Perdonador"
                AppLanguage.FRENCH -> "Le Pardonneur"
                AppLanguage.ARABIC -> "الغَفَّارُ"
                else -> english
            }
            "the provider", "the sustainer" -> when (lang) {
                AppLanguage.PORTUGUESE -> "O Provedor / Sustentador"
                AppLanguage.SPANISH -> "El Proveedor"
                AppLanguage.FRENCH -> "Le Pourvoyeur"
                AppLanguage.ARABIC -> "الرَّزَّاقُ"
                else -> english
            }
            "the all-knowing", "the omniscient" -> when (lang) {
                AppLanguage.PORTUGUESE -> "O Onisciente"
                AppLanguage.SPANISH -> "El Omnisciente"
                AppLanguage.FRENCH -> "L'Omniscient"
                AppLanguage.ARABIC -> "العَلِيمُ"
                else -> english
            }
            "the all-hearing" -> when (lang) {
                AppLanguage.PORTUGUESE -> "O Que Tudo Ouve"
                AppLanguage.SPANISH -> "El Que Todo lo Oye"
                AppLanguage.FRENCH -> "L'Audient"
                AppLanguage.ARABIC -> "السَّمِيعُ"
                else -> english
            }
            "the all-seeing" -> when (lang) {
                AppLanguage.PORTUGUESE -> "O Que Tudo Vê"
                AppLanguage.SPANISH -> "El Que Todo lo Ve"
                AppLanguage.FRENCH -> "Le Clairvoyant"
                AppLanguage.ARABIC -> "البَصِيرُ"
                else -> english
            }
            "the supreme", "the most high" -> when (lang) {
                AppLanguage.PORTUGUESE -> "O Supremo / Altíssimo"
                AppLanguage.SPANISH -> "El Altísimo"
                AppLanguage.FRENCH -> "Le Très-Haut"
                AppLanguage.ARABIC -> "العَلِيُّ"
                else -> english
            }
            else -> translateGeneralPhrase(english, lang)
        }
    }

    fun translateAsmaMeaning(meaning: String, lang: AppLanguage): String {
        if (lang == AppLanguage.ENGLISH || meaning.isBlank()) return meaning
        return translateGeneralPhrase(meaning, lang)
    }

    // --- ISLAMIC NAMES TRANSLATIONS ---
    fun translateIslamicNameMeaning(meaning: String, lang: AppLanguage): String {
        if (lang == AppLanguage.ENGLISH || meaning.isBlank()) return meaning
        return translateGeneralPhrase(meaning, lang)
    }

    fun translateIslamicNameNote(note: String?, lang: AppLanguage): String {
        if (note.isNullOrBlank() || lang == AppLanguage.ENGLISH) return note ?: ""
        return translateGeneralPhrase(note, lang)
    }

    // --- HIJRI MONTHS & EVENTS NOTES ---
    fun translateEventName(name: String, lang: AppLanguage): String {
        val trimmed = name.trim()
        val lower = trimmed.lowercase()
        return when {
            lower.contains("new year") || lower.contains("ano novo") -> when (lang) {
                AppLanguage.PORTUGUESE -> "Ano Novo Islâmico"
                AppLanguage.SPANISH -> "Año Nuevo Islámico"
                AppLanguage.FRENCH -> "Nouvel An Islamique"
                AppLanguage.ARABIC -> "رأس السنة الهجرية"
                AppLanguage.ENGLISH -> "Islamic New Year"
            }
            lower.contains("ashura") || lower.contains("achoura") -> when (lang) {
                AppLanguage.PORTUGUESE -> "Dia de Ashura"
                AppLanguage.SPANISH -> "Día de Ashura"
                AppLanguage.FRENCH -> "Jour d'Achoura"
                AppLanguage.ARABIC -> "يوم عاشوراء"
                AppLanguage.ENGLISH -> "Day of Ashura"
            }
            lower.contains("tasu'a") || lower.contains("tasua") -> when (lang) {
                AppLanguage.PORTUGUESE -> "Dia de Tasu'a"
                AppLanguage.SPANISH -> "Día de Tasu'a"
                AppLanguage.FRENCH -> "Jour de Tasu'a"
                AppLanguage.ARABIC -> "يوم تاسوعاء"
                AppLanguage.ENGLISH -> "Day of Tasu'a"
            }
            lower.contains("mawlid") || lower.contains("prophet's birthday") || lower.contains("nascimento do profeta") -> when (lang) {
                AppLanguage.PORTUGUESE -> "Nascimento do Profeta (Mawlid)"
                AppLanguage.SPANISH -> "Nacimiento del Profeta (Mawlid)"
                AppLanguage.FRENCH -> "Naissance du Prophète (Mawlid)"
                AppLanguage.ARABIC -> "المولد النبوي الشريف"
                AppLanguage.ENGLISH -> "Prophet's Birthday (Mawlid)"
            }
            lower.contains("isra") || lower.contains("miraj") || lower.contains("mi'raj") -> when (lang) {
                AppLanguage.PORTUGUESE -> "Laylat al-Miraj (A Ascensão Sagrada)"
                AppLanguage.SPANISH -> "Laylat al-Miraj (La Sagrada Ascensión)"
                AppLanguage.FRENCH -> "Laylat al-Miraj (L'Ascension Sacrée)"
                AppLanguage.ARABIC -> "الإسراء والمعراج"
                AppLanguage.ENGLISH -> "Isra and Mi'raj"
            }
            lower.contains("bara'ah") || lower.contains("mid-sha'ban") || lower.contains("mid-shaban") -> when (lang) {
                AppLanguage.PORTUGUESE -> "Noite de Bara'ah (Meio de Sha'ban)"
                AppLanguage.SPANISH -> "Noche de Bara'ah (Mitad de Sha'bán)"
                AppLanguage.FRENCH -> "Nuit de Bara'ah (Mi-Sha'ban)"
                AppLanguage.ARABIC -> "ليلة البراءة (نصف شعبان)"
                AppLanguage.ENGLISH -> "Night of Bara'ah (Mid-Sha'ban)"
            }
            lower.contains("first day of ramadan") || lower.contains("start of ramadan") || (lower.contains("ramadan") && lower.contains("start")) -> when (lang) {
                AppLanguage.PORTUGUESE -> "Início do Sagrado Ramadã"
                AppLanguage.SPANISH -> "Inicio del Sagrado Ramadán"
                AppLanguage.FRENCH -> "Début du Ramadan"
                AppLanguage.ARABIC -> "بداية شهر رمضان المبارك"
                AppLanguage.ENGLISH -> "Start of Ramadan"
            }
            lower.contains("qadr") || lower.contains("power") || lower.contains("decreto") -> when (lang) {
                AppLanguage.PORTUGUESE -> "Laylat al-Qadr (Noite do Decreto)"
                AppLanguage.SPANISH -> "Laylat al-Qadr (Noche del Destino)"
                AppLanguage.FRENCH -> "Laylat al-Qadr (Nuit du Destin)"
                AppLanguage.ARABIC -> "ليلة القدر المباركة"
                AppLanguage.ENGLISH -> "Laylat al-Qadr (Night of Power)"
            }
            lower.contains("fitr") -> when (lang) {
                AppLanguage.PORTUGUESE -> "Eid al-Fitr (Celebração do Fim do Jejum)"
                AppLanguage.SPANISH -> "Eid al-Fitr (Fiesta de Fin del Ayuno)"
                AppLanguage.FRENCH -> "Aïd el-Fitr (Fête de la Rupture)"
                AppLanguage.ARABIC -> "عيد الفطر المبارك"
                AppLanguage.ENGLISH -> "Eid al-Fitr"
            }
            lower.contains("arafah") || lower.contains("arafat") -> when (lang) {
                AppLanguage.PORTUGUESE -> "Dia de Arafah (Hajj)"
                AppLanguage.SPANISH -> "Día de Arafah (Hajj)"
                AppLanguage.FRENCH -> "Jour d'Arafat (Hajj)"
                AppLanguage.ARABIC -> "يوم عرفة المبارك"
                AppLanguage.ENGLISH -> "Day of Arafah"
            }
            lower.contains("adha") || lower.contains("sacrifice") || lower.contains("sacrifício") -> when (lang) {
                AppLanguage.PORTUGUESE -> "Eid al-Adha (Festa do Sacrifício)"
                AppLanguage.SPANISH -> "Eid al-Adha (Fiesta del Sacrificio)"
                AppLanguage.FRENCH -> "Aïd el-Adha (Fête du Sacrifice)"
                AppLanguage.ARABIC -> "عيد الأضحى المبارك"
                AppLanguage.ENGLISH -> "Eid al-Adha"
            }
            lower.contains("tashreeq") || lower.contains("tachriq") -> when (lang) {
                AppLanguage.PORTUGUESE -> "Dias de Tashreeq"
                AppLanguage.SPANISH -> "Días de Tashreeq"
                AppLanguage.FRENCH -> "Jours de Tachriq"
                AppLanguage.ARABIC -> "أيام التشريق"
                AppLanguage.ENGLISH -> "Days of Tashreeq"
            }
            else -> translateGeneralPhrase(name, lang)
        }
    }

    fun translateGregorianDate(dateStr: String?, lang: AppLanguage): String {
        if (dateStr.isNullOrBlank()) return ""
        var text: String = dateStr

        // Translate days of week
        val daysMap = when (lang) {
            AppLanguage.PORTUGUESE -> listOf(
                "Sunday" to "Domingo", "Monday" to "Segunda-feira", "Tuesday" to "Terça-feira",
                "Wednesday" to "Quarta-feira", "Thursday" to "Quinta-feira", "Friday" to "Sexta-feira",
                "Saturday" to "Sábado"
            )
            AppLanguage.SPANISH -> listOf(
                "Sunday" to "Domingo", "Monday" to "Lunes", "Tuesday" to "Martes",
                "Wednesday" to "Miércoles", "Thursday" to "Jueves", "Friday" to "Viernes",
                "Saturday" to "Sábado"
            )
            AppLanguage.FRENCH -> listOf(
                "Sunday" to "Dimanche", "Monday" to "Lundi", "Tuesday" to "Mardi",
                "Wednesday" to "Mercredi", "Thursday" to "Jeudi", "Friday" to "Vendredi",
                "Saturday" to "Samedi"
            )
            AppLanguage.ARABIC -> listOf(
                "Sunday" to "الأحد", "Monday" to "الإثنين", "Tuesday" to "الثلاثاء",
                "Wednesday" to "الأربعاء", "Thursday" to "الخميس", "Friday" to "الجمعة",
                "Saturday" to "السبت"
            )
            AppLanguage.ENGLISH -> emptyList()
        }

        for ((enDay, locDay) in daysMap) {
            text = text.replace(enDay, locDay, ignoreCase = true)
        }

        // Translate months
        val monthsMap = when (lang) {
            AppLanguage.PORTUGUESE -> listOf(
                "January" to "Janeiro", "February" to "Fevereiro", "March" to "Março",
                "April" to "Abril", "May" to "Maio", "June" to "Junho",
                "July" to "Julho", "August" to "Agosto", "Augusta" to "Agosto",
                "September" to "Setembro", "October" to "Outubro", "November" to "Novembro",
                "December" to "Dezembro"
            )
            AppLanguage.SPANISH -> listOf(
                "January" to "Enero", "February" to "Febrero", "March" to "Marzo",
                "April" to "Abril", "May" to "Mayo", "June" to "Junio",
                "July" to "Julio", "August" to "Agosto", "Augusta" to "Agosto",
                "September" to "Septiembre", "October" to "Octubre", "November" to "Noviembre",
                "December" to "Diciembre"
            )
            AppLanguage.FRENCH -> listOf(
                "January" to "Janvier", "February" to "Février", "March" to "Mars",
                "April" to "Avril", "May" to "Mai", "June" to "Juin",
                "July" to "Juillet", "August" to "Août", "Augusta" to "Août",
                "September" to "Septembre", "October" to "Octobre", "November" to "Novembre",
                "December" to "Décembre"
            )
            AppLanguage.ARABIC -> listOf(
                "January" to "يناير", "February" to "فبراير", "March" to "مارس",
                "April" to "أبريل", "May" to "مايو", "June" to "يونيو",
                "July" to "يوليو", "August" to "أغسطس", "Augusta" to "أغسطس",
                "September" to "سبتمبر", "October" to "أكتوبر", "November" to "نوفمبر",
                "December" to "ديسمبر"
            )
            AppLanguage.ENGLISH -> emptyList()
        }

        for ((enMonth, locMonth) in monthsMap) {
            text = text.replace(enMonth, locMonth, ignoreCase = true)
        }

        // Reformat common English pattern like "Domingo, Agosto 16, 2026" or "Domingo, August 16" to PT "Domingo, 16 de Agosto de 2026"
        if (lang == AppLanguage.PORTUGUESE || lang == AppLanguage.SPANISH) {
            text = text.replace(Regex("(\\b(?:Janeiro|Fevereiro|Março|Abril|Maio|Junho|Julho|Agosto|Setembro|Outubro|Novembro|Dezembro|Enero|Febrero|Marzo|Mayo|Junio|Julio|Septiembre|Octubre|Noviembre|Diciembre)\\b)\\s+(\\d{1,2}),?\\s*(\\d{4})?", RegexOption.IGNORE_CASE)) { matchResult ->
                val m = matchResult.groupValues[1]
                val d = matchResult.groupValues[2]
                val y = matchResult.groupValues.getOrNull(3)?.trim() ?: ""
                if (y.isNotEmpty()) "$d de $m de $y" else "$d de $m"
            }
        }

        return text
    }

    fun translateHijriMonthName(monthNumber: Int, lang: AppLanguage): String {
        return when (monthNumber) {
            1 -> when (lang) {
                AppLanguage.PORTUGUESE -> "Muharram"
                AppLanguage.SPANISH -> "Muhárram"
                AppLanguage.FRENCH -> "Mouharram"
                AppLanguage.ARABIC -> "مُحَرَّم"
                AppLanguage.ENGLISH -> "Muharram"
            }
            2 -> when (lang) {
                AppLanguage.PORTUGUESE -> "Safar"
                AppLanguage.SPANISH -> "Sáfar"
                AppLanguage.FRENCH -> "Safar"
                AppLanguage.ARABIC -> "صَفَر"
                AppLanguage.ENGLISH -> "Safar"
            }
            3 -> when (lang) {
                AppLanguage.PORTUGUESE -> "Rabi' al-Awwal"
                AppLanguage.SPANISH -> "Rabi' al-Awwal"
                AppLanguage.FRENCH -> "Rabi' al-Awwal"
                AppLanguage.ARABIC -> "رَبِيع الأَوَّل"
                AppLanguage.ENGLISH -> "Rabi' al-Awwal"
            }
            4 -> when (lang) {
                AppLanguage.PORTUGUESE -> "Rabi' al-Thani"
                AppLanguage.SPANISH -> "Rabi' al-Thani"
                AppLanguage.FRENCH -> "Rabi' al-Thani"
                AppLanguage.ARABIC -> "رَبِيع الثَّانِي"
                AppLanguage.ENGLISH -> "Rabi' al-Thani"
            }
            5 -> when (lang) {
                AppLanguage.PORTUGUESE -> "Jumada al-Awwal"
                AppLanguage.SPANISH -> "Yumada al-Awwal"
                AppLanguage.FRENCH -> "Joumada al-Oula"
                AppLanguage.ARABIC -> "جُمَادَى الأُولَى"
                AppLanguage.ENGLISH -> "Jumada al-Awwal"
            }
            6 -> when (lang) {
                AppLanguage.PORTUGUESE -> "Jumada al-Thani"
                AppLanguage.SPANISH -> "Yumada al-Thani"
                AppLanguage.FRENCH -> "Joumada al-Thania"
                AppLanguage.ARABIC -> "جُمَادَى الآخِرَة"
                AppLanguage.ENGLISH -> "Jumada al-Thani"
            }
            7 -> when (lang) {
                AppLanguage.PORTUGUESE -> "Rajab (Mês Sagrado)"
                AppLanguage.SPANISH -> "Ráyab (Mes Sagrado)"
                AppLanguage.FRENCH -> "Rajab (Mois Sacré)"
                AppLanguage.ARABIC -> "رَجَب (شهر حرام)"
                AppLanguage.ENGLISH -> "Rajab (Sacred Month)"
            }
            8 -> when (lang) {
                AppLanguage.PORTUGUESE -> "Sha'ban"
                AppLanguage.SPANISH -> "Sha'bán"
                AppLanguage.FRENCH -> "Cha'bane"
                AppLanguage.ARABIC -> "شَعْبَان"
                AppLanguage.ENGLISH -> "Sha'ban"
            }
            9 -> when (lang) {
                AppLanguage.PORTUGUESE -> "Ramadan (Mês do Jejum)"
                AppLanguage.SPANISH -> "Ramadán (Mes del Ayuno)"
                AppLanguage.FRENCH -> "Ramadan (Mois du Jeûne)"
                AppLanguage.ARABIC -> "رَمَضَان المُبَارَك"
                AppLanguage.ENGLISH -> "Ramadan (Month of Fasting)"
            }
            10 -> when (lang) {
                AppLanguage.PORTUGUESE -> "Shawwal"
                AppLanguage.SPANISH -> "Shawwal"
                AppLanguage.FRENCH -> "Chawwal"
                AppLanguage.ARABIC -> "شَوَّال"
                AppLanguage.ENGLISH -> "Shawwal"
            }
            11 -> when (lang) {
                AppLanguage.PORTUGUESE -> "Dhu al-Qi'dah (Mês Sagrado)"
                AppLanguage.SPANISH -> "Du al-Qa'da (Mes Sagrado)"
                AppLanguage.FRENCH -> "Dhou al-Qi'da (Mois Sacré)"
                AppLanguage.ARABIC -> "ذُو القَعْدَة (شهر حرام)"
                AppLanguage.ENGLISH -> "Dhu al-Qi'dah (Sacred Month)"
            }
            12 -> when (lang) {
                AppLanguage.PORTUGUESE -> "Dhu al-Hijjah (Mês do Hajj)"
                AppLanguage.SPANISH -> "Du al-Hiyya (Mes del Hach)"
                AppLanguage.FRENCH -> "Dhou al-Hijja (Mois du Hajj)"
                AppLanguage.ARABIC -> "ذُو الحِجَّة (شهر الحج)"
                AppLanguage.ENGLISH -> "Dhu al-Hijjah (Month of Hajj)"
            }
            else -> "Mês $monthNumber"
        }
    }

    fun translateHijriFormatted(formatted: String?, lang: AppLanguage): String {
        if (formatted.isNullOrBlank()) return ""
        var text: String = formatted
        for (i in 1..12) {
            val enNames = listOf(
                "Muharram", "Safar", "Rabi' al-awwal", "Rabi' al-Awwal", "Rabi al-Awwal",
                "Rabi' al-thani", "Rabi' al-Thani", "Rabi al-Thani",
                "Jumada al-awwal", "Jumada al-Awwal", "Jumada al-ula",
                "Jumada al-thani", "Jumada al-Thani", "Jumada al-akhirah",
                "Rajab", "Sha'ban", "Shaban", "Ramadan", "Shawwal",
                "Dhu al-Qi'dah", "Dhul-Qi'dah", "Dhu al-Hijjah", "Dhul-Hijjah"
            )
            for (en in enNames) {
                val loc = translateHijriMonthName(i, lang).substringBefore(" (")
                if (en.lowercase() in text.lowercase()) {
                    text = text.replace(en, loc, ignoreCase = true)
                }
            }
        }
        if (lang == AppLanguage.PORTUGUESE) {
            text = text.replace("AH", "Hegira (AH)")
        }
        return text
    }

    fun translateMonthNote(note: String?, lang: AppLanguage): String {
        if (note.isNullOrBlank() || lang == AppLanguage.ENGLISH) return note ?: ""
        return translateGeneralPhrase(note, lang)
    }

    fun translateIslamicInfo(text: String?, lang: AppLanguage): String {
        if (text.isNullOrBlank() || lang == AppLanguage.ENGLISH) return text ?: ""
        val trimmed = text.trim()
        val lower = trimmed.lowercase()
        return when {
            lower.contains("migration of prophet muhammad") || lower.contains("july 16, 622") -> when (lang) {
                AppLanguage.PORTUGUESE -> "16 de Julho de 622 d.C. - Migração (Hégira) do Profeta Muhammad (ﷺ) de Makkah para Medina"
                AppLanguage.SPANISH -> "16 de Julio de 622 d.C. - Emigración (Hégira) del Profeta Mahoma (ﷺ) de La Meca a Medina"
                AppLanguage.FRENCH -> "16 Juillet 622 ap. J.-C. - Émigration (Hégire) du Prophète Muhammad (ﷺ) de La Mecque à Médine"
                AppLanguage.ARABIC -> "١٦ يوليو ٦٢٢ م - هجرة النبي محمد (ﷺ) من مكة المكرمة إلى المدينة المنورة"
                AppLanguage.ENGLISH -> text
            }
            lower.contains("lunar calendar based on moon phases") -> when (lang) {
                AppLanguage.PORTUGUESE -> "Calendário lunar baseado nas fases e avistamento da lua"
                AppLanguage.SPANISH -> "Calendario lunar basado en las fases de la luna"
                AppLanguage.FRENCH -> "Calendrier lunaire basé sur les phases de la lune"
                AppLanguage.ARABIC -> "تقويم قمري يعتمد على منازل ورؤية الهلال"
                AppLanguage.ENGLISH -> text
            }
            lower.contains("vary by 1-2 days depending on moon sighting") -> when (lang) {
                AppLanguage.PORTUGUESE -> "As datas islâmicas podem variar em 1 a 2 dias dependendo do avistamento local da lua"
                AppLanguage.SPANISH -> "Las fechas islámicas pueden variar 1 o 2 días según el avistamiento de la luna"
                AppLanguage.FRENCH -> "Les dates islamiques peuvent varier de 1 à 2 jours selon l'observation de la lune"
                AppLanguage.ARABIC -> "قد تختلف المواعيد بيوم أو يومين حسب رؤية الهلال"
                AppLanguage.ENGLISH -> text
            }
            else -> translateGeneralPhrase(trimmed, lang)
        }
    }

    fun translateEventDescription(desc: String?, lang: AppLanguage): String {
        if (desc.isNullOrBlank() || lang == AppLanguage.ENGLISH) return desc ?: ""
        val trimmed = desc.trim()
        val lower = trimmed.lowercase()
        return when {
            lower.contains("beginning of new hijri year") || lower.contains("start of the hijri calendar year") -> when (lang) {
                AppLanguage.PORTUGUESE -> "Início do novo ano islâmico da Hégira."
                AppLanguage.SPANISH -> "Comienzo del nuevo año del calendario islámico de la Hégira."
                AppLanguage.FRENCH -> "Début de la nouvelle année hégirienne."
                AppLanguage.ARABIC -> "بداية العام الهجري الجديد."
                AppLanguage.ENGLISH -> desc
            }
            lower.contains("recommended fasting day, commemorating various events in islamic history") ||
            (lower.contains("recommended fasting") && lower.contains("ashura")) -> when (lang) {
                AppLanguage.PORTUGUESE -> "Dia de jejum recomendado, comemorando eventos sagrados e a salvação do Profeta Musa (Moisés) na história islâmica."
                AppLanguage.SPANISH -> "Día de ayuno recomendado, conmemorando eventos sagrados en la historia islámica."
                AppLanguage.FRENCH -> "Jour de jeûne recommandé, commémorant des événements majeurs de l'histoire islamique."
                AppLanguage.ARABIC -> "يوم صيام مستحب، يخلد نجاة نبي الله موسى وأحداثاً جليلة في التاريخ الإسلامي."
                AppLanguage.ENGLISH -> desc
            }
            lower.contains("birth of prophet muhammad") || lower.contains("prophet's birthday") || lower.contains("nascimento do profeta") -> when (lang) {
                AppLanguage.PORTUGUESE -> "Nascimento do Nobre Profeta Muhammad (que a paz e as bênçãos de Allah estejam sobre ele)."
                AppLanguage.SPANISH -> "Nacimiento del Noble Profeta Mahoma (la paz y bendiciones sean con él)."
                AppLanguage.FRENCH -> "Naissance du Noble Prophète Muhammad (paix et bénédictions sur lui)."
                AppLanguage.ARABIC -> "ذكرى المولد النبوي الشريف لسيدنا محمد (ﷺ)."
                AppLanguage.ENGLISH -> desc
            }
            lower.contains("miraculous night journey") || lower.contains("isra") || lower.contains("miraj") || lower.contains("ascension to heaven") -> when (lang) {
                AppLanguage.PORTUGUESE -> "A milagrosa viagem noturna do Profeta Muhammad (ﷺ) de Makkah a Jerusalém e a sua ascensão aos céus."
                AppLanguage.SPANISH -> "El milagroso viaje nocturno del Profeta Mahoma (ﷺ) de La Meca a Jerusalén y su ascensión a los cielos."
                AppLanguage.FRENCH -> "Le voyage nocturne miraculeux du Prophète Muhammad (ﷺ) de La Mecque à Jérusalem et son ascension céleste."
                AppLanguage.ARABIC -> "رحلة الإسراء والمعراج المباركة من المسجد الحرام إلى المسجد الأقصى والعرش."
                AppLanguage.ENGLISH -> desc
            }
            lower.contains("night of records") || lower.contains("night of forgiveness") || lower.contains("mid-sha'ban") || lower.contains("mid-shaban") || lower.contains("bara'ah") -> when (lang) {
                AppLanguage.PORTUGUESE -> "Noite do Perdão e do Decreto (Meio de Sha'ban), noite especial para orações, súplicas e busca de perdão divino."
                AppLanguage.SPANISH -> "Noche del Perdón (Mitad de Sha'bán), noche sagrada de súplicas y perdón divino."
                AppLanguage.FRENCH -> "Nuit du Pardon (Mi-Sha'ban), nuit propice aux prières et à la demande de pardon."
                AppLanguage.ARABIC -> "ليلة النصف من شعبان المباركة، ليلة المغفرة واستجابة الدعاء."
                AppLanguage.ENGLISH -> desc
            }
            lower.contains("first day of fasting") || (lower.contains("ramadan") && lower.contains("fasting")) -> when (lang) {
                AppLanguage.PORTUGUESE -> "Primeiro dia de jejum do abençoado mês sagrado do Ramadã."
                AppLanguage.SPANISH -> "Primer día de ayuno del bendito mes sagrado del Ramadán."
                AppLanguage.FRENCH -> "Premier jour de jeûne du mois sacré de Ramadan."
                AppLanguage.ARABIC -> "أول أيام الصيام في شهر رمضان المبارك."
                AppLanguage.ENGLISH -> desc
            }
            lower.contains("night of power") || lower.contains("quran was first revealed") || lower.contains("qadr") -> when (lang) {
                AppLanguage.PORTUGUESE -> "A Noite do Decreto / Poder (Laylat al-Qadr), quando o Sagrado Alcorão começou a ser revelado. Ocorre nas noites ímpares dos últimos 10 dias do Ramadã."
                AppLanguage.SPANISH -> "La Noche del Destino (Laylat al-Qadr), en la que el Corán fue revelado. Cae en las noches impares de los últimos 10 días de Ramadán."
                AppLanguage.FRENCH -> "La Nuit du Destin (Laylat al-Qadr), révélation du Coran. Située parmi les nuits impaires des 10 derniers jours de Ramadan."
                AppLanguage.ARABIC -> "ليلة القدر المباركة، خير من ألف شهر، نزل فيها القرآن الكريم."
                AppLanguage.ENGLISH -> desc
            }
            lower.contains("festival of breaking the fast") || lower.contains("end of ramadan") || lower.contains("celebrating the end") -> when (lang) {
                AppLanguage.PORTUGUESE -> "Festa de Conclusão do Jejum (Eid al-Fitr), celebração alegre pelo término abençoado do Ramadã."
                AppLanguage.SPANISH -> "Fiesta del Fin del Ayuno (Eid al-Fitr), gran celebración por la culminación del Ramadán."
                AppLanguage.FRENCH -> "Fête de la Rupture du Jeûne (Aïd el-Fitr), célébrant la fin bénie du Ramadan."
                AppLanguage.ARABIC -> "عيد الفطر المبارك، فرحة المسلمين بإتمام صيام شهر رمضان."
                AppLanguage.ENGLISH -> desc
            }
            lower.contains("pilgrims gather on mount arafat") || lower.contains("day of arafah") || lower.contains("day of arafat") -> when (lang) {
                AppLanguage.PORTUGUESE -> "Dia de Arafah - Os peregrinos reúnem-se no Monte Arafat no dia mais crucial do Hajj. Jejum altamente recomendado para quem não está em peregrinação."
                AppLanguage.SPANISH -> "Día de Arafah - Los peregrinos se reúnen en el Monte Arafat, el día cumbre del Hajj. Ayuno recomendado para los no peregrinos."
                AppLanguage.FRENCH -> "Jour d'Arafat - Rassemblement sur le mont Arafat, le pilier majeur du Hajj. Jeûne très méritoire pour les non-pèlerins."
                AppLanguage.ARABIC -> "يوم عرفة المبارك، ركن الحج الأعظم، وصيامه يكفر ذنوب سنتين لغير الحاج."
                AppLanguage.ENGLISH -> desc
            }
            lower.contains("festival of sacrifice") || lower.contains("willingness to sacrifice") || lower.contains("ibrahim") || lower.contains("eid al-adha") -> when (lang) {
                AppLanguage.PORTUGUESE -> "Festa do Sacrifício (Eid al-Adha), comemorando a obediência e fé do Profeta Ibrahim (Abraão) a Allah."
                AppLanguage.SPANISH -> "Fiesta del Sacrificio (Eid al-Adha), conmemorando la devoción del Profeta Ibrahim a Dios."
                AppLanguage.FRENCH -> "Fête du Sacrifice (Aïd el-Adha), commémorant la soumission et la dévotion du Prophète Ibrahim."
                AppLanguage.ARABIC -> "عيد الأضحى المبارك، تخليداً لطاعة واستسلام نبي الله إبراهيم عليه السلام."
                AppLanguage.ENGLISH -> desc
            }
            lower.contains("days following eid al-adha") || lower.contains("tashreeq") || lower.contains("eating, drinking") -> when (lang) {
                AppLanguage.PORTUGUESE -> "Dias de Tashreeq (11, 12 e 13 de Dhul-Hijjah) - dias sagrados de celebração, partilha e recordação constante de Allah."
                AppLanguage.SPANISH -> "Días de Tashreeq (11, 12 y 13 de Dhul-Hiyyah) - días de recuerdo a Dios y celebración."
                AppLanguage.FRENCH -> "Jours de Tachriq (11, 12 et 13 Dhul-Hijjah) - jours de joie, de partage et d'invocation d'Allah."
                AppLanguage.ARABIC -> "أيام التشريق (١١، ١٢، ١٣ ذو الحجة)، أيام أكل وشرب وذكر لله تعالى."
                AppLanguage.ENGLISH -> desc
            }
            lower.contains("annual pilgrimage to mecca") || lower.contains("hajj") -> when (lang) {
                AppLanguage.PORTUGUESE -> "Peregrinação Anual a Makkah (Hajj), quinto pilar do Islam para todos os fiéis com capacidade."
                AppLanguage.SPANISH -> "Peregrinación Anual a La Meca (Hajj), quinto pilar del Islam."
                AppLanguage.FRENCH -> "Pèlerinage Annuel à La Mecque (Hajj), cinquième pilier de l'Islam."
                AppLanguage.ARABIC -> "موسم الحج إلى بيت الله الحرام، الركن الخامس من أركان الإسلام."
                AppLanguage.ENGLISH -> desc
            }
            else -> translateGeneralPhrase(trimmed, lang)
        }
    }

    // --- CORE QURAN SURAH NAME LOCALIZATION ---
    fun getSurahNameTranslation(surahNumber: Int, defaultTranslation: String?, lang: AppLanguage): String {
        val ptTranslations = mapOf(
            1 to "A Abertura", 2 to "A Vaca", 3 to "A Família de Imran", 4 to "As Mulheres",
            5 to "A Mesa Servida", 6 to "O Gado", 7 to "Os Lugares Elevados", 8 to "Os Espólios de Guerra",
            9 to "O Arrependimento", 10 to "Jonas", 11 to "Hud", 12 to "José",
            13 to "O Trovão", 14 to "Abraão", 15 to "A Cidade de Pedra", 16 to "A Abelha",
            17 to "A Viagem Noturna", 18 to "A Caverna", 19 to "Maria", 20 to "Ta-Ha",
            21 to "Os Profetas", 22 to "A Peregrinação", 23 to "Os Crentes", 24 to "A Luz",
            25 to "O Critério", 26 to "Os Poetas", 27 to "As Formigas", 28 to "As Narrativas",
            29 to "A Aranha", 30 to "Os Bizantinos (Romanos)", 31 to "Lucman", 32 to "A Prostração",
            33 to "As Forças Aliadas", 34 to "Sabá", 35 to "O Criador Originador", 36 to "Ya-Sin",
            37 to "Os Enfileirados", 38 to "Sad", 39 to "Os Grupos", 40 to "O Perdoador",
            41 to "Versículos Detalhados", 42 to "A Consulta", 43 to "Os Ornamentos de Ouro", 44 to "A Fumaça",
            45 to "Os Ajoelhados", 46 to "As Dunas de Areia", 47 to "Muhammad", 48 to "A Vitória",
            49 to "Os Aposentos", 50 to "Qaf", 51 to "Os Ventos Dispersores", 52 to "O Monte",
            53 to "A Estrela", 54 to "A Lua", 55 to "O Misericordioso", 56 to "O Evento Inevitável",
            57 to "O Ferro", 58 to "A Mulher Reclamante", 59 to "O Exílio", 60 to "A Examinada",
            61 to "As Fileiras", 62 to "A Sexta-Feira", 63 to "Os Hipócritas", 64 to "A Desilusão Mútua",
            65 to "O Divórcio", 66 to "A Proibição", 67 to "A Soberania", 68 to "O Cálamos (A Caneta)",
            69 to "A Realidade Inevitável", 70 to "As Vias de Ascensão", 71 to "Noé", 72 to "Os Jinns",
            73 to "O Envolto no Manto", 74 to "O Embuçado", 75 to "A Ressurreição", 76 to "O Ser Humano",
            77 to "Os Enviados", 78 to "A Grande Notícia", 79 to "Os Que Arrancam", 80 to "Ele Franziu a Testa",
            81 to "O Enrolamento", 82 to "A Fenda no Céu", 83 to "Os Fraudadores", 84 to "O Rachar do Céu",
            85 to "As Grandes Constelações", 86 to "O Astro Noturno", 87 to "O Altíssimo", 88 to "O Evento Esmagador",
            89 to "A Alvorada", 90 to "A Cidade Sagrada", 91 to "O Sol", 92 to "A Noite",
            93 to "A Manhã Resplandecente", 94 to "A Abertura do Peito", 95 to "O Figo", 96 to "O Coágulo de Sangue",
            97 to "A Noite do Decreto", 98 to "A Evidência Clara", 99 to "O Terremoto", 100 to "Os Corcéis Velozes",
            101 to "A Grande Calamidade", 102 to "A Ganância Acumuladora", 103 to "O Tempo Cósmico", 104 to "O Difamador",
            105 to "O Elefante", 106 to "Os Coraixitas", 107 to "Os Pequenos Favores", 108 to "A Abundância Divina",
            109 to "Os Descrentes", 110 to "O Socorro Divino", 111 to "As Fibras de Palmeira", 112 to "A Devoção Pura",
            113 to "A Alvorada Nascente", 114 to "A Humanidade"
        )

        val esTranslations = mapOf(
            1 to "La Apertura", 2 to "La Vaca", 3 to "La Familia de Imran", 4 to "Las Mujeres",
            5 to "La Mesa Servida", 6 to "Los Ganados", 7 to "Las Alturas", 8 to "Los Botines de Guerra",
            9 to "El Arrepentimiento", 10 to "Jonás", 11 to "Hud", 12 to "José",
            13 to "El Trueno", 14 to "Abraham", 15 to "El Lugar Rocoso", 16 to "La Abeja",
            17 to "El Viaje Nocturno", 18 to "La Caverna", 19 to "María", 20 to "Ta-Ha",
            21 to "Los Profetas", 22 to "La Peregrinación", 23 to "Los Creyentes", 24 to "La Luz",
            25 to "El Criterio", 26 to "Los Poetas", 27 to "Las Hormigas", 28 to "Los Relatos",
            29 to "La Araña", 30 to "Los Romanos", 31 to "Luqmán", 32 to "La Postración",
            33 to "Los Coligados", 34 to "Saba", 35 to "El Creador", 36 to "Ya-Sin",
            37 to "Los Alineados", 38 to "Sad", 39 to "Los Grupos", 40 to "El Perdonador",
            41 to "Versículos Detallados", 42 to "La Consulta", 43 to "Los Adornos de Oro", 44 to "El Humo",
            45 to "La Arrodillada", 46 to "Las Dunas", 47 to "Mahoma", 48 to "La Victoria",
            49 to "Las Habitaciones", 50 to "Qaf", 51 to "Los Vientos Dispersores", 52 to "El Monte",
            53 to "La Estrella", 54 to "La Luna", 55 to "El Compasivo", 56 to "El Evento Inevitable",
            57 to "El Hierro", 58 to "La Discusión", 59 to "El Destierro", 60 to "La Examinada",
            61 to "Las Filas", 62 to "El Viernes", 63 to "Los Hipócritas", 64 to "La Gran Pérdida",
            65 to "El Divorcio", 66 to "La Prohibición", 67 to "La Soberanía", 68 to "El Cálamo",
            69 to "La Realidad Inevitable", 70 to "Las Vías de Ascensión", 71 to "Noé", 72 to "Los Genios",
            73 to "El Envuelto", 74 to "El Cubierto", 75 to "La Resurrección", 76 to "El Hombre",
            77 to "Los Emisarios", 78 to "La Gran Noticia", 79 to "Los Que Arrancan", 80 to "Frunció el Ceño",
            81 to "El Enrollamiento", 82 to "La Hendidura", 83 to "Los Defraudadores", 84 to "El Rasgamiento",
            85 to "Las Constelaciones", 86 to "El Astro Nocturno", 87 to "El Altísimo", 88 to "El Suceso Arrollador",
            89 to "El Alba", 90 to "La Ciudad", 91 to "El Sol", 92 to "La Noche",
            93 to "La Mañana", 94 to "La Expansión", 95 to "El Higo", 96 to "El Coágulo",
            97 to "El Destino", 98 to "La Evidencia", 99 to "El Terremoto", 100 to "Los Corceles",
            101 to "El Gran Cataclismo", 102 to "La Codicia", 103 to "La Época", 104 to "El Difamador",
            105 to "El Elefante", 106 to "Los Coraixitas", 107 to "La Ayuda", 108 to "La Abundancia",
            109 to "Los Incrédulos", 110 to "El Socorro", 111 to "La Fibra", 112 to "La Sinceridad",
            113 to "El Amanecer", 114 to "La Humanidad"
        )

        val frTranslations = mapOf(
            1 to "L'Ouverture", 2 to "La Vache", 3 to "La Famille d'Imran", 4 to "Les Femmes",
            5 to "La Table Servie", 6 to "Les Bestiaux", 7 to "Les Murailles", 8 to "Le Butin",
            9 to "Le Repentir", 10 to "Jonas", 11 to "Hud", 12 to "Joseph",
            13 to "Le Tonnerre", 14 to "Abraham", 15 to "Al-Hijr", 16 to "Les Abeilles",
            17 to "Le Voyage Nocturne", 18 to "La Caverne", 19 to "Marie", 20 to "Ta-Ha",
            21 to "Les Prophètes", 22 to "Le Pèlerinage", 23 to "Les Croyants", 24 to "La Lumière",
            25 to "Le Discernement", 26 to "Les Poètes", 27 to "Les Fourmis", 28 to "Le Récit",
            29 to "L'Araignée", 30 to "Les Romains", 31 to "Luqman", 32 to "La Prosternation",
            33 to "Les Coalisés", 34 to "Saba", 35 to "Le Créateur", 36 to "Ya-Sin",
            37 to "Les Rangés", 38 to "Sad", 39 to "Les Groupes", 40 to "Le Pardonneur",
            41 to "Les Versets Détaillés", 42 to "La Consultation", 43 to "L'Ornement", 44 to "La Fumée",
            45 to "L'Agenouillée", 46 to "Les Dunes", 47 to "Muhammad", 48 to "La Victoire",
            49 to "Les Appartements", 50 to "Qaf", 51 to "Qui Éparpillent", 52 to "Le Mont",
            53 to "L'Étoile", 54 to "La Lune", 55 to "Le Tout Miséricordieux", 56 to "L'Événement",
            57 to "Le Fer", 58 to "La Discussion", 59 to "L'Exode", 60 to "L'Éprouvée",
            61 to "Le Rang", 62 to "Le Vendredi", 63 to "Les Hypocrites", 64 to "La Grande Perte",
            65 to "Le Divorce", 66 to "L'Interdiction", 67 to "La Royauté", 68 to "La Plume",
            69 to "L'Inévitable", 70 to "Les Voies d'Ascension", 71 to "Noé", 72 to "Les Djinns",
            73 to "L'Enveloppé", 74 to "Le Revêtu d'un Manteau", 75 to "La Résurrection", 76 to "L'Homme",
            77 to "Les Envoyés", 78 to "La Grande Nouvelle", 79 to "Les Anges Qui Arrachent", 80 to "Il S'est Renfrogné",
            81 to "L'Obscurcissement", 82 to "La Rupture", 83 to "Les Fraudeurs", 84 to "La Déchirure",
            85 to "Les Constellations", 86 to "L'Astre Nocturne", 87 to "Le Très-Haut", 88 to "L'Enveloppante",
            89 to "L'Aube", 90 to "La Cité", 91 to "Le Soleil", 92 to "La Nuit",
            93 to "Le Jour Montant", 94 to "L'Ouverture", 95 to "Le Figuier", 96 to "L'Adhérence",
            97 to "La Destinée", 98 to "La Preuve", 99 to "La Secousse", 100 to "Les Coursiers",
            101 to "Le Fracas", 102 to "La Course Aux Richesses", 103 to "Le Temps", 104 to "Le Calomniateur",
            105 to "L'Éléphant", 106 to "Qoraïsh", 107 to "L'Entraide", 108 to "L'Abondance",
            109 to "Les Infidèles", 110 to "Les Secours", 111 to "Les Fibres", 112 to "Le Monothéisme Pur",
            113 to "L'Aube Naissante", 114 to "Les Hommes"
        )

        return when (lang) {
            AppLanguage.PORTUGUESE -> ptTranslations[surahNumber] ?: defaultTranslation ?: ""
            AppLanguage.SPANISH -> esTranslations[surahNumber] ?: defaultTranslation ?: ""
            AppLanguage.FRENCH -> frTranslations[surahNumber] ?: defaultTranslation ?: ""
            AppLanguage.ARABIC -> defaultTranslation ?: ""
            AppLanguage.ENGLISH -> defaultTranslation ?: ""
        }
    }

    // --- CORE QURAN AYAH LOCALIZATION ENGINE ---
    fun getAyahTranslation(ayah: com.example.data.model.AyahItem, lang: AppLanguage): String {
        val baseSahih = ayah.translations?.get("sahih_international")?.takeIf { it.isNotBlank() }
            ?: ayah.translations?.get("pickthall")?.takeIf { it.isNotBlank() }
            ?: ayah.englishTranslation

        val cleanedSahih = cleanSahihText(baseSahih)

        return when (lang) {
            AppLanguage.ARABIC -> ayah.arabic
            AppLanguage.ENGLISH -> cleanedSahih
            AppLanguage.FRENCH -> {
                val directFrench = ayah.translations?.get("french")?.takeIf { it.isNotBlank() }
                if (directFrench != null) cleanFootnotes(directFrench)
                else translateSahihToFrench(cleanedSahih)
            }
            AppLanguage.SPANISH -> {
                val directSpanish = ayah.translations?.get("spanish")?.takeIf { it.isNotBlank() }
                if (directSpanish != null) cleanFootnotes(directSpanish)
                else translateSahihToSpanish(cleanedSahih)
            }
            AppLanguage.PORTUGUESE -> {
                val directPt = ayah.translations?.get("portuguese")?.takeIf { it.isNotBlank() }
                    ?: ayah.translations?.get("pt")?.takeIf { it.isNotBlank() }
                if (directPt != null) cleanFootnotes(directPt)
                else translateSahihToPortuguese(cleanedSahih)
            }
        }
    }

    private fun cleanFootnotes(text: String): String {
        return text
            .replace(Regex("""(?<=\w|\.|,|!|\?)\d+"""), "")
            .replace(Regex("""\[\d+\]"""), "")
            .replace(Regex("""\s+"""), " ")
            .trim()
    }

    private fun cleanSahihText(text: String): String {
        return text
            .replace(Regex("""(?<=\w|\.|,|!|\?)\d+"""), "")
            .replace(Regex("""\[\d+\]"""), "")
            .replace("Allāh", "Allah")
            .replace("Muḥammad", "Muhammad")
            .replace("Ibrāhīm", "Ibrahim")
            .replace("Mūsā", "Musa")
            .replace("‘Īsā", "Isa")
            .replace("‘", "'")
            .replace("’", "'")
            .replace(Regex("""\s+"""), " ")
            .trim()
    }

    fun translateVerseText(text: String, lang: AppLanguage): String {
        if (lang == AppLanguage.ENGLISH || text.isBlank()) return cleanSahihText(text)
        return when (lang) {
            AppLanguage.PORTUGUESE -> translateSahihToPortuguese(text)
            AppLanguage.SPANISH -> translateSahihToSpanish(text)
            AppLanguage.FRENCH -> translateSahihToFrench(text)
            AppLanguage.ARABIC -> text
            AppLanguage.ENGLISH -> cleanSahihText(text)
        }
    }

    fun translateSahihToPortuguese(rawText: String): String {
        if (rawText.isBlank()) return ""
        
        // Check dynamic Juz placeholder verse pattern
        val dynamicJuzRegex = Regex("""Verse\s+(\d+)\s+from\s+Surah\s+(.+?)\s+in\s+Juz\s+(\d+)\s*-\s*Guidance\s+and\s+mercy\s+for\s+all\s+believers\.?""", RegexOption.IGNORE_CASE)
        val match = dynamicJuzRegex.find(rawText)
        if (match != null) {
            val v = match.groupValues[1]
            val s = match.groupValues[2]
            val j = match.groupValues[3]
            return "Versículo $v da Surata $s no Juz $j - Orientação e misericórdia para todos os crentes."
        }

        // Clean footnote markers like Allāh,1 or Merciful.2 and normalize transliterations
        var text = rawText
            .replace(Regex("""(?<=\w|\.|,)\d+"""), "")
            .replace("Allāh", "Allah")
            .replace("Muḥammad", "Muhammad")
            .replace("Ibrāhīm", "Ibrahim")
            .replace("Mūsā", "Musa")
            .replace("‘Īsā", "Isa")
            .replace("‘", "'")
            .replace("’", "'")
            .trim()

        // 1. Direct Verse Matches (Common Surahs and Ayat)
        val directVersesPt = mapOf(
            "In the name of Allah, the Entirely Merciful, the Especially Merciful." to "Em nome de Allah, o Clemente, o Misericordioso.",
            "In the name of Allah, the Entirely Merciful, the Especially Merciful" to "Em nome de Allah, o Clemente, o Misericordioso",
            "[All] praise is [due] to Allah, Lord of the worlds -" to "Todo o louvor pertence a Allah, Senhor do Universo -",
            "[All] praise is [due] to Allah, Lord of the worlds" to "Todo o louvor pertence a Allah, Senhor do Universo",
            "The Entirely Merciful, the Especially Merciful," to "O Clemente, o Misericordioso,",
            "The Entirely Merciful, the Especially Merciful" to "O Clemente, o Misericordioso",
            "Sovereign of the Day of Recompense." to "Soberano do Dia da Retribuição.",
            "Sovereign of the Day of Recompense" to "Soberano do Dia da Retribuição",
            "It is You we worship and You we ask for help." to "A Ti adoramos e a Ti pedimos socorro.",
            "Guide us to the straight path -" to "Guia-nos pela senda reta -",
            "Guide us to the straight path" to "Guia-nos pela senda reta",
            "The path of those upon whom You have bestowed favor, not of those who have earned [Your] anger or of those who are astray." to
                "A senda daqueles a quem agraciaste, não a dos que incorreram em Tua ira, nem a dos extraviados.",
            "Alif, Lam, Meem." to "Alif, Lam, Mim.",
            "Alif, Lam, Meem" to "Alif, Lam, Mim",
            "This is the Book about which there is no doubt, a guidance for those conscious of Allah -" to
                "Este é o Livro sobre o qual não há dúvida, um guia para os tementes a Allah -",
            "Who believe in the unseen, establish prayer, and spend out of what We have provided for them," to
                "Que creem no oculto, praticam a oração e gastam daquilo com que os sustentamos,",
            "And who believe in what has been revealed to you, [O Muhammad], and what was revealed before you, and of the Hereafter they are certain [in faith]." to
                "E que creem no que te foi revelado, [ó Muhammad], e no que foi revelado antes de ti, e têm plena certeza da Outra Vida.",
            "Those are upon [right] guidance from their Lord, and it is those who are the successful." to
                "Estes estão na reta orientação de seu Senhor, e estes são os bem-aventurados.",
            "Indeed, those who disbelieve - it is all the same for them whether you warn them or do not warn them - they will not believe." to
                "Por certo, os que descrêem - tanto lhes faz se os advertes ou não os advertes - eles não crerão.",
            "Allah has set a seal upon their hearts and upon their hearing, and over their vision is a veil. And for them is a great punishment." to
                "Allah selou os seus corações e os seus ouvidos, e sobre as suas vistas há um véu. E para eles haverá um severo castigo.",
            "Say, \"He is Allah, [who is] One," to "Dize: \"Ele é Allah, o Único,",
            "Say, \"He is Allah, [who is] One" to "Dize: \"Ele é Allah, o Único",
            "Allah, the Eternal Refuge." to "Allah, o Absoluto (a Quem todos recorrem).",
            "He neither begets nor is born," to "Não gerou e não foi gerado,",
            "Nor is there to Him any equivalent.\"" to "E ninguém é comparável a Ele.\"",
            "Say, \"I seek refuge in the Lord of daybreak" to "Dize: \"Refugio-me no Senhor da alvorada,",
            "From the evil of that which He created" to "Do mal daquilo que Ele criou,",
            "And from the evil of darkness when it settles" to "E do mal da noite quando se adensa,",
            "And from the evil of the blowers in knots" to "E do mal das que sopram nos nós,",
            "And from the evil of an envier when he envies.\"" to "E do mal do invejoso quando inveja.\"",
            "Say, \"I seek refuge in the Lord of mankind," to "Dize: \"Refugio-me no Senhor da humanidade,",
            "The Sovereign of mankind." to "O Soberano da humanidade,",
            "The God of mankind," to "O Deus da humanidade,",
            "From the evil of the retreating whisperer -" to "Do mal do sussurrador furtivo -",
            "Who whispers [evil] into the breasts of mankind -" to "Que sussurra nos corações dos seres humanos -",
            "From among the jinn and mankind.\"" to "Dentre os gênios (jinns) e os humanos.\""
        )

        for ((enVerse, ptVerse) in directVersesPt) {
            if (text.equals(enVerse, ignoreCase = true) || text.trim() == enVerse) {
                return ptVerse
            }
        }

        // 2. Phrase and Sub-clause mappings
        val phraseReplacements = listOf(
            "In the name of Allah, the Entirely Merciful, the Especially Merciful" to "Em nome de Allah, o Clemente, o Misericordioso",
            "[All] praise is [due] to Allah, Lord of the worlds" to "Todo o louvor pertence a Allah, Senhor do Universo",
            "The Entirely Merciful, the Especially Merciful" to "O Clemente, o Misericordioso",
            "the Entirely Merciful, the Especially Merciful" to "o Clemente, o Misericordioso",
            "the Entirely Merciful" to "o Clemente",
            "the Especially Merciful" to "o Misericordioso",
            "Lord of the worlds" to "Senhor do Universo",
            "Lord of the heavens and the earth" to "Senhor dos céus e da terra",
            "Sovereign of the Day of Recompense" to "Soberano do Dia da Retribuição",
            "Day of Recompense" to "Dia da Retribuição",
            "Day of Resurrection" to "Dia da Ressurreição",
            "Day of Judgement" to "Dia do Juízo Final",
            "Guide us to the straight path" to "Guia-nos pela senda reta",
            "the straight path" to "a senda reta",
            "those conscious of Allah" to "os tementes a Allah",
            "conscious of Allah" to "tementes a Allah",
            "in the unseen" to "no oculto",
            "establish prayer" to "estabelecem a oração",
            "and establish prayer" to "e estabelecem a oração",
            "the Hereafter" to "a Outra Vida",
            "they are the successful" to "eles são os bem-aventurados",
            "those who disbelieve" to "aqueles que descrêem",
            "those who believe" to "aqueles que crêem",
            "a great punishment" to "um severo castigo",
            "a painful punishment" to "um doloroso castigo",
            "a severe punishment" to "um severo castigo",
            "Gardens of Eternity" to "Jardins da Eternidade",
            "Gardens beneath which rivers flow" to "Jardins abaixo dos quais correm os rios",
            "the companions of the Fire" to "os companheiros do Fogo",
            "the companions of Paradise" to "os companheiros do Paraíso",
            "Indeed, Allah is" to "Por certo, Allah é",
            "And Allah is" to "E Allah é",
            "Forgiving and Merciful" to "Perdoador e Misericordioso",
            "All-Knowing and Wise" to "Onisciente e Sapientíssimo",
            "All-Hearing and All-Seeing" to "Que Tudo Ouve e Tudo Vê",
            "O mankind" to "Ó seres humanos",
            "O you who have believed" to "Ó vós que credes",
            "O Muhammad" to "Ó Muhammad",
            "[O Muhammad]" to "[ó Muhammad]",
            "peace be upon him" to "que a paz esteja sobre ele",
            "Messenger of Allah" to "Mensageiro de Allah",
            "worship Allah" to "adorai a Allah",
            "there is no deity except Him" to "não há divindade exceto Ele",
            "the Ever-Living, the Sustainer of all existence" to "o Vivente, o Sustentador de toda a existência",
            "No slumber can seize Him nor sleep" to "Não O toma sonolência nem sono",
            "To Him belongs whatever is in the heavens and whatever is on the earth" to "A Ele pertence tudo o que há nos céus e na terra"
        )

        var translated = text
        for ((en, pt) in phraseReplacements) {
            translated = translated.replace(en, pt, ignoreCase = false)
        }

        return translated
    }

    fun translateSahihToSpanish(rawText: String): String {
        if (rawText.isBlank()) return ""
        val text = rawText.replace(Regex("""(?<=\w|\.|,)\d+"""), "").replace("Allāh", "Alá").trim()
        val spanishPhrases = listOf(
            "In the name of Allah, the Entirely Merciful, the Especially Merciful" to "En el nombre de Alá, el Clemente, el Misericordioso",
            "[All] praise is [due] to Allah, Lord of the worlds" to "Todas las alabanzas son para Alá, Señor de los mundos",
            "The Entirely Merciful, the Especially Merciful" to "El Clemente, el Misericordioso",
            "the Entirely Merciful, the Especially Merciful" to "el Clemente, el Misericordioso",
            "Lord of the worlds" to "Señor de los mundos",
            "Sovereign of the Day of Recompense" to "Soberano del Día del Juicio",
            "Guide us to the straight path" to "Guíanos por el camino recto",
            "those who believe" to "aquellos que creen",
            "those who disbelieve" to "aquellos que no creen",
            "establish prayer" to "establecen la oración"
        )
        var res = text
        for ((en, es) in spanishPhrases) {
            res = res.replace(en, es, ignoreCase = false)
        }
        return res
    }

    fun translateSahihToFrench(rawText: String): String {
        if (rawText.isBlank()) return ""
        val text = rawText.replace(Regex("""(?<=\w|\.|,)\d+"""), "").replace("Allāh", "Allah").trim()
        val frenchPhrases = listOf(
            "In the name of Allah, the Entirely Merciful, the Especially Merciful" to "Au nom d'Allah, le Tout Miséricordieux, le Très Miséricordieux",
            "[All] praise is [due] to Allah, Lord of the worlds" to "Louange à Allah, Seigneur de l'univers",
            "The Entirely Merciful, the Especially Merciful" to "Le Tout Miséricordieux, le Très Miséricordieux",
            "the Entirely Merciful, the Especially Merciful" to "le Tout Miséricordieux, le Très Miséricordieux",
            "Lord of the worlds" to "Seigneur de l'univers",
            "Sovereign of the Day of Recompense" to "Maître du Jour de la Rétribution",
            "Guide us to the straight path" to "Guide-nous dans le droit chemin",
            "those who believe" to "ceux qui ont cru",
            "those who disbelieve" to "ceux qui ont mécru",
            "establish prayer" to "accomplissent la salat"
        )
        var res = text
        for ((en, fr) in frenchPhrases) {
            res = res.replace(en, fr, ignoreCase = false)
        }
        return res
    }

    // --- CORE GENERAL MULTI-LANGUAGE TRANSLATOR ---
    private fun translateGeneralPhrase(text: String, lang: AppLanguage): String {
        var result = text.replace(Regex("""(?<=\w|\.|,)\d+"""), "")
        val dict = when (lang) {
            AppLanguage.PORTUGUESE -> listOf(
                "Beginning of new Hijri year" to "Início do novo ano islâmico da Hégira.",
                "Beginning of new hijri year" to "Início do novo ano islâmico da Hégira.",
                "Recommended fasting day, commemorating various events in Islamic history" to
                    "Dia de jejum recomendado, comemorando eventos sagrados e a salvação do Profeta Musa na história islâmica.",
                "Birth of Prophet Muhammad (PBUH). Note: observance varies among scholars and communities." to
                    "Nascimento do Profeta Muhammad (que a paz e as bênçãos estejam sobre ele).",
                "Birth of Prophet Muhammad (PBUH)" to "Nascimento do Profeta Muhammad (ﷺ)",
                "Migration of Prophet Muhammad (PBUH) from Mecca to Medina" to
                    "Migração (Hégira) do Profeta Muhammad (ﷺ) de Makkah para Medina",
                "Lunar calendar based on moon phases" to "Calendário lunar baseado nas fases da lua",
                "Islamic dates may vary by 1-2 days depending on moon sighting" to
                    "As datas islâmicas podem variar em 1 a 2 dias dependendo do avistamento da lua",
                "O Allah, by You we enter the morning and by You we enter the evening, by You we live and by You we die, and to You is the resurrection." to
                    "Ó Allah, por Ti ingressamos na manhã e por Ti ingressamos na tarde, por Ti vivemos e por Ti morremos, e a Ti é a ressurreição.",
                "In the name of Allah, the Entirely Merciful, the Especially Merciful." to
                    "Em nome de Allah, o Clemente, o Misericordioso.",
                "In the name of Allah, the Entirely Merciful, the Especially Merciful" to
                    "Em nome de Allah, o Clemente, o Misericordioso",
                "[All] praise is [due] to Allah, Lord of the worlds -" to
                    "Todo o louvor pertence a Allah, Senhor dos mundos -",
                "[All] praise is [due] to Allah, Lord of the worlds" to
                    "Todo o louvor pertence a Allah, Senhor dos mundos",
                "The Entirely Merciful, the Especially Merciful," to
                    "O Clemente, o Misericordioso,",
                "The Entirely Merciful, the Especially Merciful" to
                    "O Clemente, o Misericordioso",
                "Sovereign of the Day of Recompense." to
                    "Soberano do Dia da Recompensa.",
                "Sovereign of the Day of Recompense" to
                    "Soberano do Dia da Recompensa",
                "It is You we worship and You we ask for help." to
                    "A Ti adoramos e a Ti pedimos socorro.",
                "Guide us to the straight path -" to
                    "Guia-nos à senda reta -",
                "Guide us to the straight path" to
                    "Guia-nos à senda reta",
                "The path of those upon whom You have bestowed favor, not of those who have earned [Your] anger or of those who are astray." to
                    "A senda daqueles a quem agraciaste, não a dos que incorreram em Tua ira, nem a dos extraviados.",
                "Say, \"He is Allah, [who is] One," to "Dize: \"Ele é Allah, o Único,",
                "Allah, the Eternal Refuge." to "Allah, o Absoluto (a Quem todos recorrem).",
                "He neither begets nor is born," to "Não gerou e não foi gerado,",
                "Nor is there to Him any equivalent.\"" to "E ninguém é comparável a Ele.\"",
                "Say, \"I seek refuge in the Lord of daybreak" to "Dize: \"Refugio-me no Senhor da alvorada,",
                "From the evil of that which He created" to "Do mal daquilo que Ele criou,",
                "And from the evil of darkness when it settles" to "E do mal da noite quando se adensa,",
                "Say, \"I seek refuge in the Lord of mankind," to "Dize: \"Refugio-me no Senhor da humanidade,",
                "The Sovereign of mankind." to "O Soberano da humanidade,",
                "The God of mankind," to "O Deus da humanidade,",
                "From the evil of the retreating whisperer -" to "Do mal do sussurrador furtivo -",
                "Who whispers [evil] into the breasts of mankind -" to "Que sussurra nos corações dos seres humanos -",
                "The One who brings everything from non-existence to existence." to
                    "Aquele que traz tudo da inexistência para a existência.",
                "The One who has plenty of mercy for the believers and the blasphemers in this world and especially for the believers in the hereafter." to
                    "Aquele que possui infinita misericórdia para com toda a criação neste mundo e especialmente para os crentes na outra vida.",
                "First month of the Islamic year. One of the four sacred months." to
                    "Primeiro mês do ano islâmico. Um dos quatro meses sagrados.",
                "The month of the Prophet's (ﷺ) birth and passing (12 Rabi al-Awwal)." to
                    "O mês do nascimento e falecimento do Profeta (ﷺ) (12 Rabi al-Awwal).",
                "One of the four sacred months. Isra wal Miraj occurred on 27 Rajab." to
                    "Um dos quatro meses sagrados. Isra e Miraj ocorreram em 27 de Rajab.",
                "The month before Ramadan. Night of Bara'ah (15 Shaban) is observed." to
                    "O mês anterior ao Ramadã. A Noite de Bara'ah (15 Shaban) é observada.",
                "The month of fasting. Laylat al-Qadr is in its last 10 nights. Quran was first revealed in Ramadan." to
                    "O mês do jejum sagrado. Laylat al-Qadr está em suas últimas 10 noites. O Alcorão começou a ser revelado no Ramadã.",
                "Eid al-Fitr is on 1 Shawwal. Six fasts of Shawwal are highly recommended." to
                    "Eid al-Fitr celebra-se em 1 de Shawwal. Os seis jejuns de Shawwal são altamente louváveis.",
                "One of the four sacred months. Pilgrims prepare for Hajj." to
                    "Um dos quatro meses sagrados. Os peregrinos preparam-se para o Hajj.",
                "Month of Hajj. First 10 days are the best days of the year. Eid al-Adha is on 10 Dhul Hijjah." to
                    "Mês do Hajj. Os primeiros 10 dias são os mais abençoados do ano. Eid al-Adha é no dia 10 de Dhul Hijjah.",
                "A classical Arabic name meaning one who is newly born or newly arrived." to
                    "Um nome árabe clássico que significa recém-nascido ou recém-chegado.",
                "Newborn, newly arrived" to "Recém-nascido, recém-chegado",
                "The reward of deeds depends upon the intentions" to "A recompensa dos atos depende das intenções",
                "and every person will get the reward according to what he has intended." to "e cada pessoa receberá a recompensa conforme a sua intenção.",
                "To believe in Allah and His Apostle" to "Crer em Allah e em Seu Mensageiro",
                "To participate in Jihad in Allah's Cause" to "Esforçar-se na causa de Allah",
                "To perform Hajj 'Mubrur'" to "Realizar a Peregrinação (Hajj) aceita por Allah",
                "Introduction to Fatihah" to "Introdução à Surah Al-Fatihah",
                "Which was revealed in Makkah" to "Que foi revelada em Makkah",
                "The Meaning of Al-Fatihah and its Various Names" to "O Significado de Al-Fatihah e seus Vários Nomes",
                "This Surah is called" to "Esta Surah é chamada",
                "the Opener of the Book" to "A Abertura do Livro",
                "the Mother of the Book" to "A Mãe do Livro (Umm Al-Kitab)",
                "according to the majority of the scholars" to "de acordo com a maioria dos sábios",
                "The virtues of Surah Al-Fatihah" to "As virtudes da Surah Al-Fatihah",
                "It was narrated that" to "Foi narrado que",
                "Abu Hurairah narrated that" to "Abu Hurairah narrou que",
                "The Messenger of Allah (ﷺ) said" to "O Mensageiro de Allah (ﷺ) disse",
                "The Messenger of Allah said" to "O Mensageiro de Allah disse",
                "Allah the Exalted said:" to "Allah, o Exaltado, disse:",
                "I have divided the prayer between Myself and My servant into two halves" to "Dividi a oração entre Mim e Meu servo em duas metades",
                "and My servant shall have what he asks for." to "e Meu servo terá aquilo que pedir.",
                "When the servant says:" to "Quando o servo diz:",
                "Allah says: 'My servant has praised Me.'" to "Allah diz: 'Meu servo Me louvou.'",
                "Allah says: 'My servant has glorified Me.'" to "Allah diz: 'Meu servo Me glorificou.'",
                "Allah says: 'My servant has magnified Me.'" to "Allah diz: 'Meu servo Me enalteceu.'",
                "This is between Me and My servant, and My servant shall have what he asks for." to "Isto é entre Mim e Meu servo, e Meu servo terá o que pedir.",
                "Tafsir of Surah Al-Baqarah" to "Tafsir da Surah Al-Baqarah",
                "The Individual Letters" to "As Letras Desconexas (Al-Muqatta'at)",
                "These letters are among the miracles of the Quran" to "Estas letras estão entre os milagres do Alcorão",
                "Guidance for the God-conscious" to "Orientação para os tementes a Allah",
                "Those who believe in the unseen" to "Aqueles que creem no oculto (Ghayb)",
                "and establish prayer and spend out of what We have provided for them" to "e estabelecem a oração e gastam daquilo com que os sustentamos",
                "Narrated" to "Narrado por",
                "Allah's Messenger (ﷺ)" to "O Mensageiro de Allah (ﷺ)",
                "Allah's Messenger" to "O Mensageiro de Allah",
                "said:" to "disse:",
                "O Allah" to "Ó Allah",
                "Lord of the worlds" to "Senhor dos mundos",
                "The Entirely Merciful" to "O Clemente",
                "The Especially Merciful" to "O Misericordioso",
                "The Creator" to "O Criador",
                "The Sustainer" to "O Sustentador",
                "The King" to "O Soberano",
                "The Holy" to "O Sagrado",
                "The Giver of Peace" to "A Fonte da Paz",
                "The Guardian" to "O Guardião",
                "The Almighty" to "O Todo-Poderoso",
                "The All-Forgiving" to "O Perdoador",
                "The All-Knowing" to "O Onisciente",
                "The All-Hearing" to "O Que Tudo Ouve",
                "The All-Seeing" to "O Que Tudo Vê",
                "The Most High" to "O Altíssimo",
                "Servant of Allah" to "Servo de Allah",
                "One who praises Allah" to "Aquele que louva a Allah",
                "Trustworthy, faithful" to "Confiável, fidedigno",
                "Rightly guided" to "Corretamente guiado",
                "Generous, noble" to "Generoso, nobre",
                "Beautiful, radiant" to "Belo, radiante",
                "Pious, righteous" to "Piedoso, virtuoso",
                "Night of Power" to "Noite do Decreto",
                "Day of Arafah" to "Dia de Arafah",
                "Prophet's Birthday" to "Nascimento do Profeta",
                "Islamic New Year" to "Ano Novo Islâmico",
                "Day of Ashura" to "Dia de Ashura",
                "Beginning of the holy fasting month" to "Início do mês sagrado de jejum",
                "The night of forgiveness and destiny" to "A noite do perdão e do decreto divino",
                "Festival of the breaking of the fast" to "Festa da quebra e conclusão do jejum",
                "The main ritual of the annual pilgrimage" to "O rito principal da peregrinação anual",
                "Feast of sacrifice commemorating Prophet Ibrahim" to "Festa do sacrifício em memória ao Profeta Ibrahim",
                "The holy journey of Prophet Muhammad to the heavens" to "A sagrada ascensão do Profeta Muhammad aos céus",
                "Birth anniversary of Prophet Muhammad (peace be upon him)" to "Aniversário de nascimento do Profeta Muhammad (que a paz esteja com ele)"
            )
            AppLanguage.SPANISH -> listOf(
                "Beginning of new Hijri year" to "Comienzo del nuevo año del calendario islámico de la Hégira.",
                "Beginning of new hijri year" to "Comienzo del nuevo año del calendario islámico de la Hégira.",
                "Recommended fasting day, commemorating various events in Islamic history" to
                    "Día de ayuno recomendado, conmemorando eventos sagrados en la historia islámica.",
                "Birth of Prophet Muhammad (PBUH). Note: observance varies among scholars and communities." to
                    "Nacimiento del Noble Profeta Mahoma (la paz y bendiciones sean con él).",
                "Birth of Prophet Muhammad (PBUH)" to "Nacimiento del Profeta Mahoma (ﷺ)",
                "O Allah, by You we enter the morning and by You we enter the evening, by You we live and by You we die, and to You is the resurrection." to
                    "Oh Alá, por Ti entramos en la mañana y por Ti entramos en la tarde, por Ti vivimos y por Ti morimos, y a Ti es la resurrección.",
                "In the name of Allah, the Entirely Merciful, the Especially Merciful." to
                    "En el nombre de Alá, el Clemente, el Misericordioso.",
                "[All] praise is [due] to Allah, Lord of the worlds -" to
                    "Todas las alabanzas son para Alá, Señor de los mundos -",
                "The reward of deeds depends upon the intentions" to "La recompensa de las obras depende de las intenciones",
                "and every person will get the reward according to what he has intended." to "y cada persona obtendrá la recompensa según su intención.",
                "First month of the Islamic year. One of the four sacred months." to
                    "Primer mes del año islámico. Uno de los cuatro meses sagrados.",
                "The month of fasting. Laylat al-Qadr is in its last 10 nights. Quran was first revealed in Ramadan." to
                    "El mes del ayuno. Laylat al-Qadr está en sus últimas 10 noches. El Corán fue revelado en Ramadán.",
                "The One who brings everything from non-existence to existence." to
                    "Aquel que trae todo de la inexistencia a la existencia.",
                "Narrated" to "Narrado por",
                "Allah's Messenger (ﷺ)" to "El Mensajero de Alá (ﷺ)",
                "said:" to "dijo:",
                "Night of Power" to "Noche del Destino",
                "Day of Arafah" to "Día de Arafah",
                "Prophet's Birthday" to "Nacimiento del Profeta",
                "Islamic New Year" to "Año Nuevo Islámico",
                "Day of Ashura" to "Día de Ashura"
            )
            AppLanguage.FRENCH -> listOf(
                "Beginning of new Hijri year" to "Début de la nouvelle année hégirienne.",
                "Beginning of new hijri year" to "Début de la nouvelle année hégirienne.",
                "Recommended fasting day, commemorating various events in Islamic history" to
                    "Jour de jeûne recommandé, commémorant des événements majeurs de l'histoire islamique.",
                "Birth of Prophet Muhammad (PBUH). Note: observance varies among scholars and communities." to
                    "Naissance du Noble Prophète Muhammad (paix et bénédictions sur lui).",
                "Birth of Prophet Muhammad (PBUH)" to "Naissance du Prophète Muhammad (ﷺ)",
                "O Allah, by You we enter the morning and by You we enter the evening, by You we live and by You we die, and to You is the resurrection." to
                    "Ô Allah, c'est par Toi que nous entrons dans le matin et par Toi dans le soir, par Toi nous vivons et par Toi nous mourons, et vers Toi est la résurrection.",
                "In the name of Allah, the Entirely Merciful, the Especially Merciful." to
                    "Au nom d'Allah, le Tout Miséricordieux, le Très Miséricordieux.",
                "[All] praise is [due] to Allah, Lord of the worlds -" to
                    "Louange à Allah, Seigneur de l'univers -",
                "The reward of deeds depends upon the intentions" to "La récompense des actes dépend des intentions",
                "and every person will get the reward according to what he has intended." to "et chaque personne sera rétribuée selon son intention.",
                "First month of the Islamic year. One of the four sacred months." to
                    "Premier mois de l'année islamique. L'un des quatre mois sacrés.",
                "The month of fasting. Laylat al-Qadr is in its last 10 nights. Quran was first revealed in Ramadan." to
                    "Le mois du jeûne. Laylat al-Qadr se trouve dans ses 10 dernières nuits. Le Coran a été révélé durant le Ramadan.",
                "The One who brings everything from non-existence to existence." to
                    "Celui qui amène toute chose du néant à l'existence.",
                "Narrated" to "Rapporté par",
                "Allah's Messenger (ﷺ)" to "Le Messager d'Allah (ﷺ)",
                "said:" to "a dit :",
                "Night of Power" to "Nuit du Destin",
                "Day of Arafah" to "Jour d'Arafat",
                "Prophet's Birthday" to "Naissance du Prophète",
                "Islamic New Year" to "Nouvel An Islamique",
                "Day of Ashura" to "Jour d'Achoura"
            )
            AppLanguage.ARABIC -> listOf(
                "Narrated" to "عن",
                "said:" to "قال:"
            )
            AppLanguage.ENGLISH -> emptyList()
        }

        for ((en, tr) in dict) {
            result = result.replace(en, tr)
        }
        return result
    }
}

