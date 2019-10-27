package Zbysiu;

class ZbysiuEditor {
    static String addChar(String string, char ch){
        String cellEdition = string;
        switch (ch) {
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                if (cellEdition.equals("0"))
                    cellEdition = "" + ch;
                else if (cellEdition.equals("-0"))
                    cellEdition = "-" + ch;
                else
                    cellEdition = cellEdition + ch;
                break;
            case ',':
            case '.':
                if (cellEdition.isEmpty())
                    cellEdition = "0.";
                else if (!cellEdition.contains("."))
                    cellEdition = cellEdition + ".";
                break;
            case '-':
                if ((cellEdition.isEmpty()) || cellEdition.equals("0"))
                    cellEdition = "-";
                break;
        }
        return (cellEdition);
    }

    static String cut(String string){
        return (string.substring(0, string.length() - 1));
    }
}
