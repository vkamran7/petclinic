package com.project.petclinic.rest;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.project.petclinic.model.Owner;

import java.io.IOException;

/**
 * @author Kamran Valizada
 */
public class JacksonCustomOwnerDeserializer extends StdDeserializer<Owner> {

    public JacksonCustomOwnerDeserializer() {
        this(null);
    }

    public JacksonCustomOwnerDeserializer(Class<Owner> t) {
        super(t);
    }

    @Override
    public Owner deserialize(JsonParser parser, DeserializationContext context) throws IOException, JacksonException {
        JsonNode node = parser.getCodec().readTree(parser);
        Owner owner = new Owner();
        String firstName = node.get("firstName").asText(null);
        String lastName = node.get("lastName").asText(null);
        String address = node.get("address").asText(null);
        String city = node.get("city").asText(null);
        String telephone = node.get("telephone").asText(null);

        if (node.hasNonNull("id")) {
            owner.setId(node.get("id").asInt());
        }

        owner.setFirstName(firstName);
        owner.setLastName(lastName);
        owner.setAddress(address);
        owner.setCity(city);
        owner.setTelephone(telephone);
        return owner;
    }
}
