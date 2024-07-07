import axios from 'axios';

const getAllUsers = async (context) => {
    try {
        const response = await axios.get('http://localhost:8081/users');
        console.log(response.data);
        context.commit('setUsers', response.data);
        
    } catch (error) {
        console.error(error);
    }
};

const authenticateUser = async (context, { email, password }) => {
    const users = context.state.users;
    const user = users.find(user => user.email === email && user.password === password);
    if (user) {
        context.commit('changeUser', user);
        return true;
    } else {
        return false;
    }
};

export default {
    state: {
        user: { username: "guest", role: "unauthorized", balance: "0" },
        users: []
    },
    mutations: {
        changeUsername(state, newUsername) {
            state.user.username = newUsername;
        },
        changeUser(state, newUser) {
            if (!newUser) {
                state.user.username = "guest";
                state.user.role = "unauthorized";
                localStorage.removeItem("user");
                return;
            }
            state.user.username = newUser.username;
            state.user.role = newUser.role;
        },
        setUsers(state, users) {
            state.users = users;
        }
    },
    getters: {
        username(state) {
            return state.user.username;
        },
        isAdmin(state) {
            return state.user.isAdmin;
        },
        role(state) {
            return state.user.role;
        },
        users(state) {
            return state.users;
        }
    },
    actions: {
        fetchUsers(context) {
            getAllUsers(context);
        },
        authenticateUser(context, credentials) {
            return authenticateUser(context, credentials);
        }
    }
}