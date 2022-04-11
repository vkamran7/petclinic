package com.project.petclinic.rest;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.project.petclinic.model.Owner;
import com.project.petclinic.model.Pet;
import com.project.petclinic.model.PetType;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Kamran Valizada
 */
public class JacksonCustomPetDeserializer extends StdDeserializer<Pet> {

    public JacksonCustomPetDeserializer(Class<Pet> t) {
        super(t);
    }

    public JacksonCustomPetDeserializer() {
        this(null);
    }
    @Override
    public Pet deserialize(JsonParser parser, DeserializationContext context) throws IOException, JacksonException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        Pet pet = new Pet();
        Owner owner = new Owner();
        PetType petType = new PetType();
        ObjectMapper mapper = new ObjectMapper();
        Date birthDate = null;
        JsonNode node = parser.getCodec().readTree(parser);
        JsonNode ownerNode = node.get("owner");
        JsonNode typeNode = node.get("type");
        owner = mapper.treeToValue(ownerNode, Owner.class);
        petType = mapper.treeToValue(typeNode, PetType.class);

        int petId = node.get("id").asInt();
        String name = node.get("name").asText(null);
        String birthDateStr = node.get("birthDate").asText(null);

        try {
            birthDate = format.parse(birthDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new IOException(e);
        }

        if (petId != 0) {
            pet.setId(petId);
        }
        pet.setName(name);
        pet.setBirthDate(birthDate);
        pet.setOwner(owner);
        pet.setType(petType);
        return pet;
    }
}
