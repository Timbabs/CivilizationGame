package view;
/**
 * This is an enum class that represents all the
 * Civilizations that the user may chose from
 *
 * @author Timothy Baba
 */
public enum CivEnum {

    ANCIENT_EGYPT {
        @Override
        public String toString() {
            return "Ancient Egypt";
        }
    },
    QIN_DYNASTY {
        @Override
        public String toString() {
            return "Qin Dynasty";
        }
    },
    ROMAN_EMPIRE {
        @Override
        public String toString() {
            return "Roman Empire";
        }
    },
    ANCIENT_CHINA {
        @Override
        public String toString() {
            return "Ancient China";
        }
    },
    America {
        @Override
        public String toString() {
            return "America";
        }
    },
    Aztec {
        @Override
        public String toString() {
            return "Aztec";
        }
    }
}