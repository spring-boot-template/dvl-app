let headers = new Headers();
let vueList = null;
let vueItem = null;

let selectedChip = null;

let globalFilterOn = false;

const $ = (selector) => {
    return document.querySelectorAll(selector);
};

const $1 = (selector) => {
    return document.querySelector(selector);
};

/**
 * Iterates over main elements if main exists.
 * 
 * @param main
 *            main element.
 * @param elements
 *            elements from main
 * @param consumer
 *            forEach consumer of elements
 * @return main for reference
 */
const $from = (main, elements, consumer) => {
    let element = document.querySelector(main);
    if (!element) return null;
    
    document.querySelectorAll(elements).forEach(consumer);
    return element;
};

const $onchange = (el, func) => {
	el.addEventListener("change", e => {
		func(e);
	});
};

const $onclick = (el, func) => {
	el.addEventListener("click", e => {
		func(e);
	});
};

const $onblur = (el, func) => {
	el.addEventListener("blur", e => {
		func(e);
	});
};

const $onkeydown = (el, func) => {
	el.addEventListener("keydown", e => {
		func(e);
	});
};

const $onkeyup = (el, func) => {
	el.addEventListener("keyup", e => {
		func(e);
	});
};

const $onenter = (el, func) => {
	el.addEventListener("keydown", e => {
	    if (e.keyCode == 13) {
	        e.preventDefault();
	        func(e);
	    }
	});
};

const needsConfirmation = (element) => {
	return element.dataset.confirm;
};

/**
 * Defines the page title based on the URL, if the page defines a #title
 */
const initTitle = () => {
    let title = $1("#title");
    if (!title) return;
    
    let url = App.hash(); 
    if (!url) return;
    
    let name = url.substr(url.lastIndexOf("v=")).replace("v=", "");
    App.debug("defining title as "+name);
    title.innerHTML = name + " " + title.innerHTML; 
};

const initLinks = () => {
	App.debug("making links...");
    $(".link").forEach(link => {
    	App.trace(link.outerHTML);
        let href = link.dataset.href;
        App.trace(href);

        if (!href) return;
        App.debug("adding click for "+href);

        $onclick(link, (e) => {
        	App.trace("adding click");
                
            if (!e.ctrlKey) {
                if (!needsConfirmation(link)) {
                    window.document.location = href;
                } else {
                    let ok = confirm(link.dataset.confirm);
                    if (ok) window.document.location = href;
                }
            } else {
                window.open(href, "_blank");
            }
        });
    });
}

/**
 * Binds form post action
 */
const initForm = () => {
    let form = $1("#form");
    if (!form) return;

    let idInput = form.querySelector("#id");
    let id = idInput.value;
    App.debug("checking form id: "+form.getAttribute("name")+"#"+id);
    idInput.focus();
    
    let submit = form.querySelector("button[type='submit']");
    if (submit) {
    	$onclick(submit, e => {
    		e.preventDefault();
    		App.post(form.getAttribute("name"));
    	});
    }
    
    form.querySelectorAll("input").forEach(input => $onchange(input, (event) => {
    	if (!id || input.id === "id") {
    		App.debug("creating new bean");
    		App.post(form.getAttribute("name"), (id) => {
    			document.location.href = "./edit.htm#"+id;
    			App.config("new-bean", "true");
    			App.refresh();
    		});
    		
    		return;
    	}

    	if ("disabled" != form.dataset.autosave) {
			App.debug("autosaving: "+form.dataset.autosave);
			App.post(form.getAttribute("name"));
		}
    }));
};

const initCollapse = () => {
	let elems = document.querySelectorAll('.collapsible');
    let instances = M.Collapsible.init(elems, {
    	  accordion: true
    });
};

/**
 * Global search filter. Care should be taken not to keep adding events
 */
const initGlobalFilter = () => {
	if (globalFilterOn) return;
	globalFilterOn = true;
	
	let filter = $1('#filter-box');
	if (!filter) return;
	
	$onenter(filter, (e) => {
		App.debug(filter.value);
		App.getList(App.path()+"/like?id="+filter.value);
	});
};

const initToggle = () => {
	let elems = $('.toggle');
    elems.forEach(item => {
    	App.debug("toggle: ",item);
    	item.checked = App.toggle(item.dataset.filter);
    	$onchange(item, e => App.toggle(item.dataset.filter, item.checked));
    });
};

const initTooltips = () => {
    let elems = document.querySelectorAll('.tooltipped');
    M.Tooltip.init(elems);
};

const initAutocomplete = () => {
	let elems = document.querySelectorAll('.autocomplete');
    let instances = M.Autocomplete.init(elems);
    instances.forEach(instance => {
    	if (!instance.el.dataset.autocomplete) return;
    	
    	App.trace("instance: ", instance.el.name);
    	App.autocomplete(instance.el.dataset.autocomplete, data => {
    		App.trace("autocomplete data: ", data);
    		instance.updateData(data);
    	});
    });
};

const initModals = () => {
	let elems = document.querySelectorAll('.modal');
    let instances = M.Modal.init(elems);
    
    instances.forEach(instance => {
    	let modal = instance.el;
    	if (!modal.dataset.delete) return;
    	
    	App.debug("modal ok: DEL "+modal.dataset.delete);
    	
    	let confirm = modal.querySelector(".modal-confirm");
    	if (!confirm) {
    		App.debug("WARN: delete modal has no confirm button");
    		return;
    	}
    	
    	$onclick(confirm, e => {
    		e.preventDefault();
    		App.debug("calling delete action for "+modal.id);
    		App.del(modal.dataset.delete, () => {
    			let link = confirm.getAttribute("href");
    			let refresh = link.includes(window.location.hash)? true : false;
    			
    			window.location.href = link;
    			
    			if (refresh) App.refresh();
    		});
    	});
    });
};

const exceptionHandling = (response) => {
	if (!response.ok) {
        throw Error(response.statusText + " - "+ response.code);
    }
    return response;
};

App = function() {
	let _ = {};
	
	_ = {
		debug : (msg, object) => {
			if (console && _.configTrue("dvl.debug")) {
				if (object) console.log(msg, object); else console.log(msg);
			}
        },
        trace : (msg, object) => {
			if (console && _.configTrue("dvl.trace")) {
				_.debug(msg, object);
			}
        },
        configTrue : (name) => {
        	return window.localStorage.getItem(name) == "true";
        },
        submit : (refresh = false, onComplete) => {
        	_.post($1("#form").getAttribute("name"), () => {
        		if (refresh == true) _.refresh();
        		if (onComplete) onComplete();
        	});
        },
        removeConfig : (name) => {
        	return window.localStorage.removeItem(name);
        },
        defines : (key) => {
            if (!localStorage.getItem(key)) return false;
            return true;
        },
        config : (name, value) => {
        	return value? 
        			window.localStorage.setItem(name, value)
        			:window.localStorage.getItem(name);
        },
        toggle : (name, value) => {
        	if (!_.config("toggle")) _.config("toggle", JSON.stringify(Array.from(new Map([[name, value]]))));
        	
        	let map = new Map(JSON.parse(_.config("toggle")));
        	if (value == undefined) return map.get(name);
        	
        	_.debug("toggling "+name+" to "+value);
        	
        	map.set(name, value)
        	_.trace("toggles: ", map.entries());
        	_.config("toggle", JSON.stringify(Array.from(new Map(map))));
        	_.refresh();
        },
        toggles : (handler) => {
        	let map = new Map(JSON.parse(_.config("toggle")));
        	if (!handler) return map;
        	
        	return map.forEach((v, k) => handler(k, v));
        },
        path : () => {
            return window.location.pathname.split("/")[1];
        },
        hash : (concat) => {
        	let hash = window.location.hash.substr(1);
            if (!concat) {
            	return hash;
            } else {
            	// URL sanitizing
            	if (concat.includes("?")) {
            		return concat + (hash.startsWith("?")? hash.substring(1) : hash);
            	} else {
            		return concat + hash;
            	}
            }
        },
        error : (exception) => {
        	_.debug("FAIL response: "+exception);
            M.toast({html: exception, classes: "red"});
            _.loadingEnd();
        },
        success : (message = 'Changes saved') => {
            M.toast({html: message});
            _.loadingEnd();
        },
        loadingStart : () => {
        	_.trace("loading...");
        	$1("body").insertAdjacentHTML("afterbegin", '<div class="progress"><div class="indeterminate"></div></div>');
        },
        loadingEnd : () => {
        	$(".progress").forEach(node => node.remove());
        },
        refresh : () => {
        	_.debug("refreshing...");
        	document.location.reload();
        },
        api : (service) => {
        	_.loadingStart();
        	
            let url = service;
            if (!service.startsWith("/")) {
            	url = (_.config(_.VAR_API_PATH) || "/api") + "/"+url

            	_.toggles((k, v) => {
            		_.trace("setting header "+k+"="+v);
            		headers.set("dvl-toggle-"+k, v);
            	});
            }
            
            _.debug("GET "+url);
            return url;
        },
        beanId : () => {
            return $1("#form").querySelector("#id").value;
        },
        get : (service, container = "vue-item", onComplete) => {
            fetch(_.api(service))
                .then(response => {
                    return response.json();
                })
                .then(json => {
                	vueItem = new Vue({
                        el: '#'+container,
                        data: {
                            item: json
                        },
                        mounted: () => {
                            _.mounted(onComplete);
                        }
                    });
                	_.debug("GET VUE: ", vueItem.data);
                })
        },
        getList : (service, container = "vue-list", onComplete) => {
            if (_.hash()) service = _.hash(service);
            
            fetch(_.api(service), { method: 'GET', credentials: 'include', headers: headers })
                .then(response => {
                    return response.json();
                })
                .then(json => {
                	if (vueList) {
                		vueList.list = json;
                		Vue.nextTick(() => {
                			_.mounted(onComplete);
                		});
                	} else {
                		vueList = new Vue({
                			el: '#'+container,
                			data: {
                				list: json
                			},
                			methods: {
                                fdouble: (value) => {
                                	return parseFloat(Math.round(value * 100) / 100).toFixed(2);
                                },
                                favg: (value, count) => {
                                	let d = value / (count < 1? 1 : count)
                                	return parseFloat(Math.round(d * 100) / 100).toFixed(2);
                                }
                            },
                			mounted: () => {
                				_.mounted(onComplete);
                			}
                		});
                	}
                })
        },
        /**
		 * @param service
		 *            API path
		 * @param onComplete
		 *            handler for returned text response
		 */
        getText : (service, onComplete) => {
            fetch(_.api(service))
                .then(response => {
                    return response.text();
                })
                .then(text => {
                	onComplete(text);
                })
        },
        autocomplete : (service, callback) => {
            fetch(_.api(service)+"/autocomplete")
                .then(response => {
                    return response.json();
                })
                .then(json => {
                    callback(json);
                })
        },
        /**
		 * Posts a form to a service
		 * 
		 * @param service
		 *            to post to
		 */
        post : (service, onComplete) => {
        	headers.set("Accept", "application/json");
        	headers.set("Content-Type", "application/json");

        	let form = $1("#form");
            _.debug("POST: "+ JSON.stringify(form2js(form)));
            
            if (!vueItem) {
            	App.error("no data to post");
            	return;
            }
            
            fetch(_.api(service), {method: 'POST', credentials: 'include', headers: headers, body: JSON.stringify(form2js(form))})
            	.then(exceptionHandling)
                .then(response => {
                    return response.json();
                })
                .then(json => {
                    _.debug("POST response: "+ JSON.stringify(json));
                    if (onComplete) onComplete(json && json.id);

                    _.success();
                })
                .catch(exception => {
                    _.error(exception);
                })
            ;
        },
        /**
		 * Performs a delete operation
		 */
        del : (service, onComplete) => {
            _.debug("DELETE: "+service);
            fetch(_.api(service), {method: 'DELETE', credentials: 'include'})
            	.then(exceptionHandling)
                .then(response => {
                    _.success();
                    onComplete();
                })
                .catch(exception => {
                    _.error(exception);
                })
            ;
        },
        /**
		 * Doc ready hook. Considers if Vue.js is mounted as well.
		 */
        mounted : (onComplete) => {
            _.debug("< MOUNTING >");
            initLinks();
            initTitle();
            initForm();
            initCollapse();
            initAutocomplete();
            initToggle();
            initTooltips();
            initModals();
            initGlobalFilter();
            
            if (_.configTrue("new-bean")) {
            	M.toast({html: 'New entity created', classes: "green"});
            	_.removeConfig("new-bean");
            }
            
            // @see tags.js
            _.start(Tags);
            
            if (onComplete) onComplete();
          
            _.loadingEnd();
        },
        start : (lib) => {
        	if (typeof lib !== "undefined") {
				_.debug("initializing lib: "+lib.id());
				lib.appReady();
			}
        },
		init : () => {
			_.debug("init...");
			
			if (!_.defines(_.VAR_API_PATH)) {
				fetch("/env?var=dvl.rest.prefix")
				.then(response => {
					return response.text();
				})
				.then(val => {
					_.config(_.VAR_API_PATH, val);
					_.debug("val: "+val);
				})
				;
			}
			return "ok";
		}
    };
    
    _.VAR_API_PATH = "dvl.API_PATH";

	_.init();
	return _;
}();
