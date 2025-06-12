export default {
  inserted(el, binding, vnode) {
    let role = vnode.context.$store.getters.role;
    if (!binding.value.includes(role) && el.parentElement) {
      el.parentElement.removeChild(el);
    }
  },
  update(el, binding, vnode) {
    let role = vnode.context.$store.getters.role;
    if (!binding.value.includes(role) && el.parentElement) {
      el.parentElement.removeChild(el);
    }
  },
};
