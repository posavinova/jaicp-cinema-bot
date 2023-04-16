require: slotfilling/slotFilling.sc
  module = sys.zb-common
theme: /

    state: Start
        q!: $regex</start>
        a: Приветствую! Я робот-консультант кинотеатра <name>. Чем я могу вам помочь?
        intent: /акции || toState = "/CategoryType"
        intent: /акции и льготы/др || toState = "/BirthDay"
        intent: /акции и льготы/группы || toState = "/Group"
        intent: /акции и льготы/особые категории || toState = "/Discounts"
        event: noMatch || toState = "/NoMatch"

    state: NoMatch
        event!: noMatch
        a: Пока что я не знаю, как ответить на ваш вопрос: {{$request.query}}

    state: CategoryType
        a: В нашем кинотеатре действует несколько акций, а также льготная программа. Выберите, что наиболее актуально для вас:
        buttons:
            "День рождения" -> /BirthDay
            "В кино большой компанией" -> /Group
            "Особые льготы" -> /Discounts
        if: 

    state: BirthDay
        a: В День рождения каждый именинник может посмотреть любой фильм в нашем кинотеатре совершенно бесплатно.

    state: Discounts
        a: Школьники, студенты, пенсионеры, ветераны и многодетные семьи могут купить билет за полцены, но только при наличии документа, подтверждающего льготу.

    state: Group
        a: При покупке 5 и более билетов вам будет предоставлена скидка в размере 30%.