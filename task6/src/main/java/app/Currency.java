package app;

public class Currency {
    private int numCode;
    private String code;
    private int lot;
    private String name;
    private float value;

    public Currency(int numCode, String code, int lot, String name, float value) {
        this.numCode = numCode;
        this.code = code;
        this.lot = lot;
        this.name = name;
        this.value = value;
    }

    public String toString(){
        String result = (this.getLot() + " " +
                this.getName() + " = " +
                this.getValue()
        );
        String valueStr = Float.toString(this.getValue());
        char lastChar = valueStr.charAt(valueStr.length() - 1);
        if (lastChar == '1'){
            result += " Российский рубль";
        }
        else if (lastChar == '2' || lastChar == '3' || lastChar == '4'){
            result += " Российских рубля";
        }
        else{
            result += " Российских рублей";
        }
        return result;
    }

    public int getNumCode() {
        return numCode;
    }

    public void setNumCode(int numCode) {
        this.numCode = numCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getLot() {
        return lot;
    }

    public void setLot(int lot) {
        this.lot = lot;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
