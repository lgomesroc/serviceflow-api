import axios from 'axios'

const baseURL =
  import.meta.env.VITE_API_URL || 'http://localhost:8080/api'

const api = axios.create({
  baseURL,
  headers: {
    'Content-Type': 'application/json',
  },
})

const authBaseURL = baseURL.replace(/\/api\/?$/, '')

const authApi = axios.create({
  baseURL: authBaseURL,
  headers: {
    'Content-Type': 'application/json',
  },
})

api.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem(
      'serviceflow_token',
    )

    if (token) {
      config.headers.Authorization = `Bearer ${token}`
    }

    return config
  },
  (error) => Promise.reject(error),
)

api.interceptors.response.use(
  (response) => response,
  (error) => {
    if (
      error.response?.status === 401 ||
      error.response?.status === 403
    ) {
      localStorage.removeItem('serviceflow_token')

      if (window.location.pathname !== '/login') {
        window.location.href = '/login'
      }
    }

    return Promise.reject(error)
  },
)

export async function login(username, password) {
  const response = await authApi.post('/auth/login', {
    username,
    password,
  })

  return response.data
}

export function logout() {
  localStorage.removeItem('serviceflow_token')
}

export async function getServiceRequests(
  page = 0,
  size = 10,
) {
  const response = await api.get('/service-requests', {
    params: {
      page,
      size,
    },
  })

  return response.data
}

export async function createServiceRequest(request) {
  const response = await api.post(
    '/service-requests',
    request,
  )

  return response.data
}

export default api
