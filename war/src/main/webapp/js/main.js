var chapters = [{
	"name":"Functions and Limits",
	"sections": [{
		"name":"Four Ways to Represent a Function",
		"problems":"2"
	}]
}];

function problemClick() {
	var self = $(this);
	var url = "chapter" + self.attr("data-chapter-number") + 
		"/section" + self.attr("data-section-number") + 
		"/problem" + self.attr("data-problem-number") +
		"/definition.html";

	$("#problem").load(url);
}

function sectionClick() {
	var self = $(this);
	var problemsTag = $("#problems");
	problemsTag.empty();
	for(var i = 0; i < parseInt(self.attr("data-problems-count")); ++i) {
		var problemTag = $("<button>");
		problemTag.attr("data-chapter-name", self.attr("data-chapter-name"));
		problemTag.attr("data-chapter-number", self.attr("data-chapter-number"));
		problemTag.attr("data-section-name", self.attr("data-section-number"));
		problemTag.attr("data-section-number", self.attr("data-section-number"));
		problemTag.attr("data-problem-number", i+1);
		problemTag.text(i+1);
		problemTag.click(problemClick);
		problemsTag.append(problemTag);
	}
}

function chapterClick() {
	var self = $(this);
	var sectionsTag = $("#sections");
	sectionsTag.empty();
	var sections = chapters[parseInt(self.attr("data-chapter-number"))-1].sections;
	for(var i = 0; i < sections.length; ++i) {
		var sectionTag = $("<button>");
		sectionTag.attr("data-chapter-name", self.attr("data-chapter-name"));
		sectionTag.attr("data-chapter-number", self.attr("data-chapter-number"));
		sectionTag.attr("data-section-name", sections[i].name);
		sectionTag.attr("data-section-number", i+1);
		sectionTag.attr("data-problems-count", sections[i].problems);
		sectionTag.text(sections[i].name);
		sectionTag.click(sectionClick);
		sectionsTag.append(sectionTag);
	}
}

$(function() {
	for(var i = 0; i < chapters.length; ++i) {
		var id = "chapter" + i + "link";
		var chapter = $("<button>");
		chapter.attr("id", id);
		chapter.attr("data-chapter-name", chapters[i].name);
		chapter.attr("data-chapter-number", i+1);
		chapter.text(chapters[i].name);
		$(chapter).click(chapterClick);
		$("#chapters").append(chapter);
	}
});
