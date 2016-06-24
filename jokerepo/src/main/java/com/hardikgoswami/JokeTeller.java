package com.hardikgoswami;

import java.util.Random;

public class JokeTeller {

    String jokes[] = {
            "How do you make God laugh? Tell him your plans",
            "If God is watching us, the least we can do is be entertaining.",
            "Deja Vu – When you think you're doing something you've done before, it's because God thought it was so funny, he had to rewind it for his friends",
            "God must love stupid people. He made SO many",
            "Dear Lord, there is a bug in your software...it's called #Monday, please fix it...",
            "Some cause happiness wherever they go. Others whenever they go"
    };
    public String fetchJoke(){
        Random rm = new Random();
        return jokes[rm.nextInt(jokes.length)];
    }

}
