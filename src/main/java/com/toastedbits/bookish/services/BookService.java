package com.toastedbits.bookish.services;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.toastedbits.bookish.models.Book;
import com.toastedbits.bookish.models.Category;

@Service
public class BookService {
	public Map<Integer, Book> getBooks(Category category) {
		Map<Integer, Book> books = new LinkedHashMap<Integer, Book>();
		
		Book sample = new Book();
		sample.setId(1);
		sample.setImage("http://placekitten.com/200/300");
		sample.setLink("#");
		sample.setTitle("Calculus");
		sample.setSummary("Calculus");
		books.put(sample.getId(), sample);
		
		sample = new Book();
		sample.setId(2);
		sample.setImage("http://placekitten.com/200/300");
		sample.setLink("#");
		sample.setTitle("Physics");
		sample.setSummary(generateLoremIpsum());
		books.put(sample.getId(), sample);
		
		return books;
	}
	
	public static String generateLoremIpsum() {
		return " Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec ligula enim, iaculis quis interdum sit amet, eleifend eget risus. Integer convallis, odio non rutrum ullamcorper, dui nisi fermentum ante, sit amet pretium ligula odio non nunc. Nunc ac ligula at risus fringilla elementum. Donec ut lacinia orci. Nulla semper orci vel tellus volutpat gravida. Aliquam suscipit pellentesque porttitor. Duis fermentum lorem et nulla euismod non adipiscing nunc lacinia. Phasellus porttitor mi vel est posuere vel condimentum ipsum blandit." +
				"Proin in quam vitae metus tristique ultrices eu eu lorem. Cras tempor luctus nisi, eu consectetur nisi posuere eu. Praesent est orci, commodo porttitor gravida ultrices, elementum nec mi. Vivamus porttitor commodo nunc quis laoreet. Maecenas sodales nibh laoreet tellus faucibus eget dignissim ligula venenatis. Nulla odio nisi, facilisis ac adipiscing in, pharetra eu mi. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Vivamus velit mi, ultricies quis pharetra sed, convallis vel purus. Etiam et nisl at nunc posuere malesuada." +
				"Donec vehicula pharetra accumsan. Curabitur est elit, sodales vitae sagittis sed, ultricies at sem. Praesent at est mi, quis aliquet enim. Sed accumsan, massa vitae vulputate ultrices, lacus diam consectetur nulla, id semper libero tortor nec diam. Quisque imperdiet augue non quam iaculis ac tempor diam sodales. Duis at ante id justo hendrerit pellentesque. Quisque vel nisi turpis, vel tincidunt augue. Praesent sit amet mi molestie odio dictum aliquet. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos." +
				"Donec porta nisi id dui elementum ut mattis lorem semper. Ut et nisl dolor. Aenean urna sem, cursus sit amet rutrum sit amet, scelerisque eget libero. Curabitur placerat rutrum lorem, fringilla dignissim enim venenatis in. Quisque gravida accumsan felis, sit amet tristique lectus lobortis et. Maecenas vitae augue at orci accumsan auctor quis vel tellus. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Sed orci urna, tincidunt dapibus dapibus id, gravida eget dolor. Fusce lacinia risus vitae sapien mollis facilisis." +
				"Nam in ante a eros vestibulum consequat. Praesent tristique luctus tellus, at placerat tortor lacinia eget. Nulla ut nisl metus, rutrum pulvinar risus. Donec ac nulla ac metus dictum imperdiet eu id felis. Integer bibendum hendrerit euismod. Suspendisse bibendum lacus id lorem adipiscing nec vestibulum eros porttitor. Donec vehicula elit non leo condimentum varius. Etiam rhoncus, nulla ac ultricies euismod, risus augue mollis justo, egestas scelerisque sem lorem bibendum eros. ";
	}
}