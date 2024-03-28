package mazeneko.mazewands.example.member.repository;

import java.util.List;

import org.seasar.doma.jdbc.criteria.Entityql;
import org.seasar.doma.jdbc.criteria.NativeSql;
import org.seasar.doma.jdbc.criteria.expression.Expressions;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import mazeneko.mazewands.example.member.domain.Member;
import mazeneko.mazewands.example.member.domain.MemberQuery;
import mazewands.persistence.Identifier;

@Repository
@RequiredArgsConstructor
class MemberQueryImpl implements MemberQuery {
  private final Entityql entityql;
  private final NativeSql nativeSql;

  private static final MemberEntity_ member_ = new MemberEntity_();
  private static final MemberNameAliasEntity_ alias_ = new MemberNameAliasEntity_();

  @Override
  public long count() {
    return this.nativeSql
        .from(member_)
        .select(Expressions.count())
        .fetchOne();
  }

  @Override
  public List<Identifier<Member>> filterByExists(List<Identifier<Member>> ids) {
    if (ids.isEmpty()) {
      return List.of();
    }
    final var existsIds = this.nativeSql
        .from(member_)
        .where(c -> c.in(member_.id, ids))
        .select(member_.id)
        .fetch();
    return ids.stream().filter(existsIds::contains).toList();
  }

  @Override
  public List<Member> getAsPossibleByIds(List<Identifier<Member>> ids) {
    return this.entityql
        .from(member_)
        .innerJoin(alias_, on -> on.eq(member_.id, alias_.memberId))
        .associateWith(member_, alias_, MemberEntity::associate)
        .where(c -> c.in(member_.id, ids))
        .stream()
        .map(MemberEntity::toDomain)
        .toList();
  }

  @Override
  public List<Member> getAll() {
    return this.entityql
        .from(member_)
        .innerJoin(alias_, on -> on.eq(member_.id, alias_.memberId))
        .associateWith(member_, alias_, MemberEntity::associate)
        .stream()
        .map(MemberEntity::toDomain)
        .toList();
  }
}
