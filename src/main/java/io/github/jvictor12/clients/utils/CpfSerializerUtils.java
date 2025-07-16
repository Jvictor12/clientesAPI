package io.github.jvictor12.clients.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import io.github.jvictor12.clients.exception.ValidationException;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CpfSerializerUtils extends JsonSerializer<String> {

    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value != null && value.length() == 11 && value.matches("\\d{11}")) {
            // Coloca a máscara ###.###.###-##
            String maskedCpf = value.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
            gen.writeString(maskedCpf);
        } else {
            throw new ValidationException("Invalid CPF provided");
        }
    }
}
