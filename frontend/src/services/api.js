import axios from 'axios'

const api = axios.create({
  baseURL:
    import.meta.env.VITE_API_URL || 'http://localhost:8080/api',
  headers: {
    'Content-Type': 'application/json',
  },
})

export async function getServiceRequests(page = 0, size = 10) {
  const response = await api.get('/service-requests', {
    params: {
      page,
      size,
    },
  })

  return response.data
}

export async function createServiceRequest(request) {
  const response = await api.post('/service-requests', request)

  return response.data
}

export default api
