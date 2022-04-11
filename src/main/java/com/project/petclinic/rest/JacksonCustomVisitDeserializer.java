package com.project.petclinic.rest;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.project.petclinic.model.Pet;
import com.project.petclinic.model.Vet;
import com.project.petclinic.model.Visit;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Kamran Valizada
 */
public class JacksonCustomVisitDeserializer extends StdDeserializer<Visit> {

    public JacksonCustomVisitDeserializer(Class<Visit> t) {
        super(t);
    }

    public JacksonCustomVisitDeserializer() {
        this(null);
    }

    @Override
    public Visit deserialize(JsonParser parser, DeserializationContext context) throws IOException, JacksonException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        Visit visit = new Visit();
        Pet pet = new Pet();
        ObjectMapper objectMapper = new ObjectMapper();
        Date visitDate = null;
        JsonNode node = parser.getCodec().readTree(parser);
        JsonNode petNode = node.get("pet");
        pet = objectMapper.treeToValue(petNode, Pet.class);
        int visitId = node.get("id").asInt();
        String visitDateStr = node.get("date").asText(null);
        String description = node.get("description").asText(null);
        Boolean adHoc = node.get("adHoc").asBoolean(false);
        Boolean scheduler = node.get("scheduled").asBoolean(false);
        Boolean isPaid = node.get("isPaid").asBoolean(false);
        JsonNode vetNode = node.get("vet");
        Vet vet = objectMapper.treeToValue(vetNode, Vet.class);
        try {
            visitDate = format.parse(visitDateStr);
        } catch (ParseException ex) {
            ex.printStackTrace();
            throw new IOException(ex);
        }

        if (visitId != 0) {
            visit.setId(visitId);
        }
        visit.setDate(visitDate);
        visit.setDescription(description);
        visit.setPet(pet);
        visit.setVet(vet);
        visit.setAdHoc(adHoc);
        visit.setScheduled(scheduler);
        visit.setPaid(isPaid);
        return visit;
    }
}
