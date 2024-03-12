import users from "./users.json"
export default {
    state: {
        user: { username: "guest", role: "unauthorized" },
        users: users
    },
    mutations: {
        changeUsername(state, newUsername) {
            state.user.username = newUsername
        },
        changeUser(state, newUser) {

            if (!newUser) {
                state.user.username = "guest"
                state.user.role = "unauthorized"
                localStorage.removeItem("user")
                return
            }

            state.user.username = newUser.username,
                state.user.role = newUser.role
        }
    },
    getters: {
        username(state) {
            return state.user.username
        },
        isAdmin(state) {
            return state.user.isAdmin
        },
        role(state) {
            return state.user.role
        },
        users(state) {
            return state.users
        }
    }
}