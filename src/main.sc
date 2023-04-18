require: slotfilling/slotFilling.sc
  module = sys.zb-common
theme: /

    state: Start
        q!: $regex</start>
        a: Приветствую! Я робот-консультант кинотеатра. Чем я могу вам помочь?
        intent: /акции || toState = "/CategoryType"
        intent: /акции и льготы/др || toState = "/Birthday"
        intent: /акции и льготы/группы || toState = "/Group"
        intent: /акции и льготы/особые категории || toState = "/Discounts"
        event: noMatch || toState = "/NoMatch"

    state: NoMatch
        event!: noMatch
        a: Пока что я не знаю, как ответить на ваш вопрос.
        Confirmation: 
            prompt = Хотите переключиться на оператора?
            agreeState = /Transfer
            disagreeState = /Farewell
            useButtons = true
            agreeButton = Да, переключите меня
            disagreeButton = Нет, спасибо

    state: CategoryType
        a: В нашем кинотеатре действует несколько акций, а также льготная программа. Выберите, что наиболее актуально для вас:
        buttons:
            "День рождения" -> /Birthday
            "В кино большой компанией" -> /Group
            "Особые льготы" -> /Discounts

    state: Birthday
        a: В День рождения каждый именинник может посмотреть любой фильм в нашем кинотеатре совершенно бесплатно.
        intent: /ok || toState = "/Farewell"
        intent: /детали || toState = "/Redirect"
        event: noMatch || toState = "/NoMatch"

    state: Discounts
        a: Школьники, студенты, пенсионеры, ветераны и многодетные семьи могут купить билет за полцены, но только при наличии документа, подтверждающего льготу.
        intent: /ok || toState = "/Farewell"
        intent: /детали || toState = "/Redirect"
        intent: /акции и льготы/документы || toState = "./"
        event: noMatch || toState = "/NoMatch"

    state: Group
        a: При покупке 5 и более билетов вам будет предоставлена скидка в размере 30%.
        intent: /ok || toState = "/Farewell"
        intent: /детали || toState = "/Redirect"
        event: noMatch || toState = "/NoMatch"

    state: Farewell
        random: 
            a: Рад, что смог вам помочь!
            a: Обращайтесь!
            a: Хорошего дня!

    state: Redirect
        a: Простите, на данный момент это всё, что я могу подсказать. С деталями акций и специальных предложений вы можете ознакомиться на нашем официальном сайте <link>.

    state: Transfer
        a: Вашим вопросом уже занимаются...