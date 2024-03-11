create table if not exists member (
  id uuid not null,
  name text not null,
  primary key (id)
);
create table if not exists member_name_alias (
  member_id uuid not null,
  name text not null,
  primary key (member_id, name)
);