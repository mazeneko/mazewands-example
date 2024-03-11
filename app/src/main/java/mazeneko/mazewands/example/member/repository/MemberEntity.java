package mazeneko.mazewands.example.member.repository;

import java.util.ArrayList;
import java.util.List;

import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Metamodel;
import org.seasar.doma.Table;
import org.seasar.doma.Transient;

import lombok.NonNull;
import mazeneko.mazewands.example.member.domain.Member;
import mazeneko.mazewands.persistence.Identifier;

@Entity(immutable = true, metamodel = @Metamodel)
@Table(name = "member")
record MemberEntity(
    @NonNull @Id Identifier<Member> id,
    @NonNull String name,
    @NonNull @Transient List<MemberNameAliasEntity> aliases) {

  MemberEntity(Identifier<Member> id, String name) {
    this(id, name, new ArrayList<>());
  }

  MemberEntity associate(MemberNameAliasEntity alias) {
    assert this.id.equals(alias.memberId());
    this.aliases.add(alias);
    return this;
  }

  Member toDomain() {
    assert this.aliases().stream().map(MemberNameAliasEntity::memberId).allMatch(this.id()::equals);
    return new Member(
        this.id(),
        this.name(),
        this.aliases().stream().map(MemberNameAliasEntity::name).toList());
  }

  static MemberEntity from(Member member) {
    final var aliases = member
        .aliases()
        .stream()
        .map(name -> new MemberNameAliasEntity(member.id(), name))
        .toList();
    return new MemberEntity(member.id(), member.name(), aliases);
  }
}

@Entity(immutable = true, metamodel = @Metamodel)
@Table(name = "member_name_alias")
record MemberNameAliasEntity(
    @NonNull @Id Identifier<Member> memberId,
    @NonNull @Id String name) {
}