export default {
    inserted: function(el, binding, vnode) {
        console.log(el)
        console.log(vnode.context.$store.getters.role)
        console.log(binding.value)

        let role = vnode.context.$store.getters.role

        if(!binding.value.includes(role)) {
            vnode.elm.parentElement.removeChild(vnode.elm)
        }
    },
    update: function(el, binding, vnode) {
        console.log(el)
        console.log(vnode.context.$store.getters.role)
        console.log(binding.value)

        let role = vnode.context.$store.getters.role

        if(!binding.value.includes(role)) {
            vnode.elm.parentElement.removeChild(vnode.elm)
        }
    }
}