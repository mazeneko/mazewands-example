package mazeneko.mazewands.example.config.doma;

import org.seasar.doma.DomainConverters;

@DomainConverters({
    IdentifierConverter.class,
    UUIDConverter.class,
})
public class DomainConverterProvider {
}
