<r:script>
    function customWikiFormat(cellvalue, options, rowObject) {
        // format the cellvalue to new format
        var authorTokens = cellvalue.split(' ');
        var author = '';
        authorTokens.map(function (item) {
            author = author + "_" + item;
        })
        return "<a href='http://en.wikipedia.org/wiki/" + author.substring(1) + "'>" + cellvalue + "</a> ";
    }
</r:script>

<r:require modules="easygrid-jqgrid-dev,export"/>

<grid:grid id='jqgridinitial' name='requerimientos' jqgrid.caption="'Requerimientos'" jqgrid.width='"1200"' columns.name.jqgrid.formatter='customWikiFormat'/>
<%--
<grid:exportButton name='requerimientos'/>
--%>
