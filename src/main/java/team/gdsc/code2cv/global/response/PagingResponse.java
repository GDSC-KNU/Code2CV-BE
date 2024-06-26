package team.gdsc.code2cv.global.response;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import lombok.Builder;

@Builder
public record PagingResponse<T>(
	Integer totalPage,
	List<T> data
) {
	public static <T> PagingResponse<T> of(List<T> data, Integer totalPage) {
		return PagingResponse.<T>builder()
			.totalPage(totalPage)
			.data(data)
			.build();
	}

	public static <Entity, Model> PagingResponse<Model> from(Page<Entity> data, Function<Entity, Model> converter) {
		List<Model> convertedData = data.getContent().stream()
			.map(converter)
			.collect(Collectors.toList());

		return PagingResponse.<Model>builder()
			.totalPage(data.getTotalPages())
			.data(convertedData)
			.build();
	}
}
