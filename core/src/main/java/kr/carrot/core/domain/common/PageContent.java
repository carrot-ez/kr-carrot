package kr.carrot.core.domain.common;

import java.util.Collection;

public record PageContent<T>(int page, int size, long total, Collection<T> content) {
}
