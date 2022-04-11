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
public class JacksonCustomOwnerSerializer extends StdSerializer<Owner> {

    public JacksonCustomOwnerSerializer() {
        this(null);
    }

    public JacksonCustomOwnerSerializer(Class<Owner> t) {
        super(t);
    }

    @Override
    public void serialize(Owner owner, JsonGenerator gen, SerializerProvider provider) throws IOException {
        Format format = new SimpleDateFormat("yyyy/MM/dd");
        gen.writeStartObject();

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

        // Write pets array
        gen.writeArrayFieldStart("pets");
        for (Pet pet : owner.getPets()) {
            gen.writeStartObject(); // pet

            if (pet.getId() == null) {
                gen.writeNullField("id");
            } else {
                gen.writeNumberField("id", pet.getId());
            }

            gen.writeStringField("name", pet.getName());
            gen.writeStringField("birthDate", format.format(pet.getBirthDate()));

            PetType type = pet.getType();
            gen.writeObjectFieldStart("type");
            gen.writeNumberField("id", type.getId());
            gen.writeStringField("name", type.getName());
            gen.writeEndObject(); // type

            if (pet.getOwner().getId() == null) {
                gen.writeNullField("owner");
            } else {
                gen.writeNumberField("owner", pet.getOwner().getId());
            }

            // Write visits array
            gen.writeArrayFieldStart("visits");
            for (Visit visit : pet.getVisits()) {
                gen.writeStartObject(); // visit
                if (visit.getId() == null) {
                    gen.writeNullField("id");
                } else {
                    gen.writeNumberField("id", visit.getId());
                }
                gen.writeStringField("date", format.format(visit.getDate()));
                gen.writeStringField("description", visit.getDescription());
                gen.writeNumberField("pet", visit.getPet().getId());
                gen.writeEndObject(); // visit
            }
            gen.writeEndArray(); // visits
            gen.writeEndObject(); // pet
        }
        gen.writeEndArray(); // pets
        gen.writeEndObject(); // owner
    }
}
