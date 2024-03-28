package mazeneko.mazewands.example.config.spring;

import java.util.UUID;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import mazewands.persistence.Identifier;

@Component
public class StringToIdentifierConverter implements Converter<String, Identifier<?>> {

  @Override
  public Identifier<?> convert(@Nullable String source) {
    return Identifier.of(UUID.fromString(source));
  }
}
