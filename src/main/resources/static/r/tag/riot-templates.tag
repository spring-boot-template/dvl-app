<delete-refresh>
<a if="{ state.id }" class="btn waves-effect waves-light red modal-trigger tooltipped"
	data-tooltip="Delete" 
	href="#delete">
	<i class="material-icons">remove_circle</i>
</a>
<div if="{ state.id }" id="delete" class="modal" data-delete={ state.deleteUrl }>
	<div class="modal-content">
		<h4>Delete</h4>
		<p>Delete '{ state.shortId }' from { opts.from }?</p>
	</div>
	<div class="modal-footer">
		<a href="./list.htm" class="modal-confirm waves-effect waves-green btn-flat">Yes</a>
		<a href="#" class="modal-close waves-effect waves-green btn-flat">Cancel</a>
	</div>
</div>

<script>
let idArr = id.split("=");

this.state = {
	id: opts.id,
	deleteUrl: opts.from+opts.id,
	shortId: idArr[idArr.length-1]
}
</script>
</delete-refresh>

<go-home>
<a id="home" href="/" class="brand-logo">
	<i class="material-icons">home</i>
</a>
</go-home>

<head>
	<meta charset="UTF-8">
	<title>dvl-core.it - Manager - { opts.title }</title>
	<link rel="icon" href="/r/img/logo-tri.png" />
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">

	<link rel="stylesheet" href="/r/lib/materialize/_.css">
	<link rel="stylesheet" href="/r/lib/materialize/icons.css">
	<link rel="stylesheet" href="/r/css/app.css">
</head>

<ls-delete>
<a if="{ state.id }" class="btn waves-effect waves-light red modal-trigger tooltipped"
	data-tooltip="Delete" 
	href="#delete">
	<i class="material-icons">remove_circle</i>
</a>
<div if="{ state.id }" id="delete" class="modal" data-delete={ state.deleteUrl }>
	<div class="modal-content">
		<h4>Delete</h4>
		<p>Delete '{ state.shortId }' from { opts.from }?</p>
	</div>
	<div class="modal-footer">
		<a href="./list.htm" class="modal-confirm waves-effect waves-green btn-flat">Yes</a>
		<a href={ state.cancelHref } class="modal-close waves-effect waves-green btn-flat">Cancel</a>
	</div>
</div>

<script>
let hash = App.hash();
let tagId = opts.id;
let id = tagId || hash;
let idArr = id.split("=");

this.state = {
	id: id,
	deleteUrl: opts.from+(id && id.includes("&")? '' : '/')+id,
	shortId: idArr[idArr.length-1],
	cancelHref: "#"+id
}
</script>
</ls-delete>

<ls-footer>
<footer class="page-footer secondary">
    <div class="container">
        <div class="row">
            <div class="col l6 s12">
                <h5 class="white-text">DVL Manager v<get data-url="/env/version" /></h5>
                <p class="grey-text text-lighten-4">
                    Manage everything.
                </p>
            </div>
        </div>
    </div>
    <div class="footer-copyright">
        <div class="container">
            dvl-core.it
        </div>
    </div>
</footer>
</ls-footer>

<ls-header-list>
<nav class="primary" role="navigation">
    <div class="nav-wrapper container">
        <go-home></go-home>
        <ls-logout></ls-logout>
        
        <a href="#" data-target="nav-mobile" class="sidenav-trigger">
            <i class="material-icons">menu</i>
        </a>
    </div>
</nav>

<div class="section no-pad-bot" id="index-banner">
    <div class="container">
        <h1 class="header center secondary-text">{ state.subject }</h1>
        <div class="row center">
            <h5 class="header col s12 light">
            	All managed { state.subject }.
            	<yield/>
            </h5>
        </div>
    </div>
</div>

<section id="filter" if="{ state.filterable }">
	<input id="filter-box" type="text" placeholder="filter">
</section>

<script>
let filterable = window.location.pathname.includes("list.htm");
this.state = {
	filterable: filterable,
	subject: opts.all
}
</script>
</ls-header-list>

<ls-header>
<nav class="primary" role="navigation">
    <div class="nav-wrapper container">
        <go-home></go-home>
        
        <ls-logout></ls-logout>
        
        <a href="#" data-target="nav-mobile" class="sidenav-trigger">
            <i class="material-icons">menu</i>
        </a>
    </div>
</nav>
</ls-header>

<ls-logout>
<ul class="right hide-on-med-and-down">
	<li>
		<a href="/logout" class="tooltipped" data-tooltip="Logout">
			<i class="material-icons">exit_to_app</i>
		</a>
	</li>
</ul>
<ul id="nav-mobile" class="sidenav">
	<li>
		<a href="/logout" class="tooltipped" data-tooltip="Logout">
			<i class="material-icons">exit_to_app</i>
		</a>
	</li>
</ul>
</ls-logout>

<ls-toggle>
<div class="switch">
	<label title="{ opts.title }">
		{ opts.off }
		<input class="toggle" type="checkbox" data-filter="{ opts.filter }">
		<span class="lever"></span>
		{ opts.on }
	</label>
</div>
</ls-toggle>