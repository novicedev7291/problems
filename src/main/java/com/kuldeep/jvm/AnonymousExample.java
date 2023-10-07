package com.kuldeep.jvm;

import java.util.function.Function;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
class AnonymousExample {
    private Function<String, String> fn = new Function<String, String>() {
        @Override
        public String apply(String s) {
            return s + " returned";
        }
    };
}
