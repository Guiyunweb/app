const data = {}

data.set = function (key, data) {
    return localStorage.setItem(key, JSON.stringify(data))
}

data.get = function (key) {
    return JSON.parse(localStorage.getItem(key))
}

data.remove = function (key) {
    return localStorage.removeItem(key)
}

data.clear = function () {
    return localStorage.clear()
}

export default data
