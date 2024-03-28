package mazeneko.mazewands.example.member.domain;

import java.util.List;

import lombok.NonNull;
import mazewands.persistence.Identifiable;
import mazewands.persistence.Identifier;

public record Member(
    @NonNull Identifier<Member> id,
    @NonNull String name,
    @NonNull List<String> aliases) implements Identifiable<Member> {
}
