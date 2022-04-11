package com.project.petclinic.rest;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.project.petclinic.model.Owner;
import com.project.petclinic.model.Pet;
import com.project.petclinic.model.PetType;
import com.project.petclinic.model.Visit;

import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;

/**
 * @author Kamran Valizada
 */
public class JacksonCustomVisitSerializer extends StdSerializer<Visit> {

    public JacksonCustomVisitSerializer(Class<Visit> t) {
        super(t);
    }

    public JacksonCustomVisitSerializer() {
        this(null);
    }

    @Override
    public void serialize(Visit visit, JsonGenerator gen, SerializerProvider provider) throws IOException {

        if (visit == null || visit.getPet() == null) {
            throw new IOException("Cannot serialize Visit object - visit or visit.pet is null");
        }

        Format format = new SimpleDateFormat("yyyy/MM/dd");
        gen.writeStartObject(); // visit
        if (visit.getId() == null) {
            gen.writeNullField("id");
        } else {
            gen.writeNumberField("id", visit.getId());
        }
        gen.writeStringField("date", format.format(visit.getDate()));
        gen.writeStringField("description", visit.getDescription());
        if (visit.getAdHoc() != null) {
            gen.writeBooleanField("adHoc", visit.getAdHoc());
        }
        if (visit.getScheduled() != null) {
            gen.writeBooleanField("scheduled", visit.getScheduled());
        }
        if (visit.getPet() != null) {
            gen.writeBooleanField("isPaid", visit.getPaid());
        }

        Pet pet = visit.getPet();
        gen.writeObjectFieldStart("pet");
        if (pet.getId() == null) {
            gen.writeNullField("id");
        } else {
            gen.writeNumberField("id", pet.getId());
        }
        gen.writeStringField("name", pet.getName());
        gen.writeStringField("birthDate", format.format(pet.getBirthDate()));

        PetType type = pet.getType();
        gen.writeObjectFieldStart("type");
        if (type.getId() == null) {
            gen.writeNullField("id");
        } else {
            gen.writeNumberField("id", type.getId());
        }
        gen.writeStringField("name", type.getName());
        gen.writeEndObject(); // type

        Owner owner = pet.getOwner();
        gen.writeObjectFieldStart("owner");
        if (owner.getId() == null) {
            gen.writeNullField("id");
        } else {
            gen.writeNumberField("id", owner.getId());
        }
        gen.writeStringField("firstName", owner.getFirstName());
        gen.writeStringField("lastName", owner.getLastName());
        gen.writeStringField("address", owner.getAddress());
        gen.writeStringField("city", owner.getCity());
        gen.writeStringField("telephone", owner.getTelephone());
        gen.writeEndObject(); // owner
        gen.writeEndObject(); // pet
        gen.writeEndObject(); // visit
    }
}
