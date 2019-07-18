/**
 * Body: code
 * @param lang highlight type
 * @since 1.2.4
 */
Vue.component('vue-code', {
	props: ['lang'],
	template: `<code id="log" :class="'language-'+lang"><slot></slot></code>`
});

/**
 * Body: material icon
 * @param tip tooltip
 * @param href link
 * @param enabled condition to enable or disable button
 * @param color css
 * @since 1.2.4
 */
Vue.component('vue-btn', {
	props: ['tip', 'href', 'enabled', 'color'],
	template: `
		<a :class="(color? color+' ':'')+'btn waves-effect waves-light tooltipped'+(enabled? '':' disabled')" target="_blank"
            :data-tooltip="tip"
        	:href="href">
            <i class="material-icons">
            	<slot></slot>
            </i>
        </a>
	`
});

/**
 * Body: material icon
 * @param tip tooltip
 * @param href link
 * @param enabled condition to enable or disable link
 * @param color css
 * @since 1.2.4
 */
Vue.component('vue-link', {
	props: ['tip', 'href', 'enabled', 'color'],
	template: `
		<a :class="(color? color+' ':'')+'tooltipped'+(enabled? '':' disabled')"
            :data-tooltip="tip"
        	:href="href">
            <i class="material-icons">
            	<slot></slot>
            </i>
        </a>
	`
});

/**
 * Body: material icon
 * @param color css
 * @since 1.2.4
 */
Vue.component('vue-return-to-list', {
	props: ['color'],
	template: `
		<a id="return" :class="'btn waves-effect waves-light '+(color? color+' ':'')+'tooltipped'"
            data-tooltip="Return to List"
        	href="./list.htm">
            <i class="material-icons">
            	keyboard_return
            </i>
        </a>
	`
});

/**
 * Body: material icon
 * @param tip tooltip
 * @param href link
 * @since 1.2.4
 */
Vue.component('vue-delete', {
	props: ['id', 'return-to', 'from', 'enabled'],
	data: () => {
		return {
			prettyId: (id) => {
				return id+"lol";
			}
	    }
	},
	template: `
		<span>
			<a :class="'btn waves-effect waves-light red modal-trigger tooltipped'+(enabled? '':' disabled')"
				data-tooltip="Remove"
				:href="'#delete-'+id">
				<i class="material-icons">remove_circle</i>
			</a>
			<div v-if="enabled" :id="'delete-'+id" class="modal" :data-delete="from+'?'+id">
				<div class="modal-content">
					<h4>Delete</h4>
					<p>Delete <slot></slot> from {{ from }}?</p>
				</div>
				<div class="modal-footer">
					<a :href="'./edit.htm#'+returnTo" class="modal-confirm waves-effect waves-green btn-flat">Yes</a>
					<a :href="'#'+returnTo" class="modal-close waves-effect waves-green btn-flat">Cancel</a>
				</div>
			</div>
		</span>
	`
});
