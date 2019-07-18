Tags = function() {
	let _ = {};
	_ = {
		id : () => {
			return "Tags";
		},
		/**
		 * Performs e GET request and expects a TEXT response. Immediately writes it to the document.
		 */
		get : () => {
			document.querySelectorAll("get").forEach(tag => {
				if (!tag.dataset.url) {
					App.debug("data-url attribute is required for <get> tags", tag);
					return;
				}
				
				App.getText(tag.dataset.url, (text) => {
					tag.innerHTML = (tag.dataset.prefix || "") + text;
				});
			});
		},
		/**
		 * Performs e GET request and expects a TEXT response. Immediately writes it to the attribute after "get". e.g.: data-get-src will perform a GET and set the result to "src".
		 */
		getData : () => {
			document.querySelectorAll(".get").forEach(tag => {
				for (let i = 0; i < tag.attributes.length; i++) {
				    let attr = tag.attributes[i];
				    if (/^data-get-/.test(attr.nodeName)) {
				    	App.getText(attr.nodeValue, (text) => {
				    		let target = attr.nodeName.replace(/^data-get-/, '');
							App.debug("got "+text+" from "+attr.nodeName+". setting to "+target);
							tag.setAttribute(target, text);
						});
				    }
				}
			});
		},
		/**
		 * Prints the current URL hash
		 */
		hash : () => {
			document.querySelectorAll("hash").forEach(tag => {
				if (!tag.dataset.for) {
					tag.innerHTML = App.hash();
				} else {
					App.hash().split("&").map(item => {
						console.log("iimap: "+item+", for: "+tag.dataset.for);
						if (item.includes(tag.dataset.for+"=")) {
							tag.innerHTML = item.split("=")[1];
							console.log("ok! "+item.split("=")[1]+", inner: "+tag.innerHTML);
							return null;
						}
					});
				}
			});
		},
		/**
		 * Called when the app finishes loading (including Vue.js)
		 */
        appReady : () => {
        	App.debug("mounting tags...");
            _.get();
            _.hash();
            _.getData();
        },
		init : () => {
			App.debug("tags: init tags...");
			return "ok";
		}
    };
    
	_.init();
	return _;
}();
