package entyties;

import java.io.Serializable;

public class Command implements Serializable {
    private String mac;
    private DrawingElement element;

    public Command(String mac, DrawingElement element) {
        this.mac = mac;
        this.element = element;
    }

    public String getMac() {
        return mac;
    }

    public DrawingElement getElement() {
        return element;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    @Override
    public String toString() {
        return "Command{" +
                "mac='" + mac + '\'' +
                ", element=" + element +
                '}';
    }
}
