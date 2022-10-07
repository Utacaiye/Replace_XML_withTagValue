package Wa;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class Baru {
    private String ikanLaut = "Kura-Kura";
    private String hewanDarat = "Kucing";

    private String selainHewan ="Setan";

    private String dariCahaya = "Malaikat";

    public Baru(){

    }

    public Baru(String ikanLaut, String hewanDarat) {
        this.ikanLaut = ikanLaut;
        this.hewanDarat = hewanDarat;
    }

    public String getIkanLaut() {
        return ikanLaut;
    }

    public void setIkanLaut(String ikanLaut) {
        this.ikanLaut = ikanLaut;
    }

    public String getHewanDarat() {
        return hewanDarat;
    }

    public void setHewanDarat(String hewanDarat) {
        this.hewanDarat = hewanDarat;
    }

    public String getSelainHewan() {
        return selainHewan;
    }

    public void setSelainHewan(String selainHewan) {
        this.selainHewan = selainHewan;
    }

    public String getDariCahaya() {
        return dariCahaya;
    }

    public void setDariCahaya(String dariCahaya) {
        this.dariCahaya = dariCahaya;
    }
}