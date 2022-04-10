package com.project.petclinic.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Simple domain object to represent a list of veterinarians. Mostly here to be used for the 'vets'
 * {@link org.springframework.web.servlet.view.xml.MarshallingView}
 *
 * @author Kamran Valizada
 */
@XmlRootElement
public class Vets {

    private List<Vet> vets;

    @XmlElement
    public List<Vet> getVetList() {
        if (this.vets == null) {
            this.vets = new ArrayList<>();
        }
        return this.vets;
    }
}
