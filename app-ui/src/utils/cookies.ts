import Cookies from 'js-cookie'

function set(key: string, value: any) {
  Cookies.set(key, value)
}

function setExpires(key: string, value: any, expires: number) {
  Cookies.set(key, value, {expires: expires})
}

function get(key: string): string | undefined {
  return Cookies.get(key)
}

function remove(key: string) {
  Cookies.remove(key)
}

export const cookies = {
  set,
  setExpires,
  get,
  remove
}
