package mazeneko.mazewands.example.member.domain;

import java.util.List;

import lombok.NonNull;
import mazeneko.mazewands.persistence.Identifiable;
import mazeneko.mazewands.persistence.Identifier;

public record Member(
    @NonNull Identifier<Member> id,
    @NonNull String name,
    @NonNull List<String> aliases) implements Identifiable<Member> {
}
