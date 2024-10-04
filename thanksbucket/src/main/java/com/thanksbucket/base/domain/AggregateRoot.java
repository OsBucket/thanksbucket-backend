package com.thanksbucket.base.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AggregateRoot<T extends DomainEntity<T, TID>, TID> extends
    DomainEntity<T, TID> {


}
