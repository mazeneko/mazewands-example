package mazeneko.mazewands.example.member.repository;

import java.util.List;

import org.seasar.doma.jdbc.criteria.Entityql;
import org.seasar.doma.jdbc.criteria.NativeSql;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import mazeneko.mazewands.example.member.domain.Member;
import mazeneko.mazewands.example.member.domain.MemberCommand;
import mazewands.persistence.Identifier;

@Repository
@RequiredArgsConstructor
class MemberCommandImpl implements MemberCommand {
  private final Entityql entityql;
  private final NativeSql nativeSql;

  private static final MemberEntity_ member_ = new MemberEntity_();
  private static final MemberNameAliasEntity_ alias_ = new MemberNameAliasEntity_();

  @Override
  public List<Member> saveAll(List<Member> resources) {
    final var entities = resources.stream().map(MemberEntity::from).toList();
    final var aliases = entities.stream().map(MemberEntity::aliases).flatMap(List::stream).toList();
    this.entityql.insert(member_, entities).execute();
    this.entityql.insert(alias_, aliases).execute();
    return resources;
  }

  @Override
  public List<Member> updateAll(List<Member> resources) {
    final var entities = resources.stream().map(MemberEntity::from).toList();
    final var aliases = entities.stream().map(MemberEntity::aliases).flatMap(List::stream).toList();
    this.entityql.update(member_, entities).execute();
    this.entityql.update(alias_, aliases).execute();
    return resources;
  }

  @Override
  public long deleteByIds(List<Identifier<Member>> ids) {
    if (ids.isEmpty()) {
      return 0;
    }
    this.nativeSql
        .delete(alias_)
        .where(c -> c.in(alias_.memberId, ids))
        .execute();
    return this.nativeSql
        .delete(member_)
        .where(c -> c.in(member_.id, ids))
        .execute();
  }

  @Override
  public long deleteAll() {
    this.nativeSql
        .delete(alias_, settings -> settings.setAllowEmptyWhere(true))
        .execute();
    return this.nativeSql
        .delete(member_, settings -> settings.setAllowEmptyWhere(true))
        .execute();
  }
}
