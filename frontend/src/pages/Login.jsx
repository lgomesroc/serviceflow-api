import { useState } from 'react'
import { useLocation, useNavigate } from 'react-router-dom'
import { AlertCircle, LogIn } from 'lucide-react'
import { login } from '../services/api.js'

function Login() {
  const navigate = useNavigate()
  const location = useLocation()

  const [username, setUsername] = useState('')
  const [password, setPassword] = useState('')
  const [loading, setLoading] = useState(false)
  const [error, setError] = useState('')

  async function handleSubmit(event) {
    event.preventDefault()

    setError('')

    if (!username.trim() || !password) {
      setError('Informe o usuário e a senha.')
      return
    }

    try {
      setLoading(true)

      const data = await login(
        username.trim(),
        password,
      )

      localStorage.setItem('serviceflow_token', data.token)

      const destination =
        location.state?.from?.pathname || '/'

      navigate(destination, { replace: true })
    } catch (error) {
      console.error('Erro ao realizar login:', error)

      if (
        error.response?.status === 401 ||
        error.response?.status === 403
      ) {
        setError('Usuário ou senha inválidos.')
      } else {
        setError(
          'Não foi possível realizar o login. Verifique se a API está disponível e tente novamente.',
        )
      }
    } finally {
      setLoading(false)
    }
  }

  return (
    <div className="min-vh-100 bg-light d-flex align-items-center">
      <main className="container py-5">
        <div className="row justify-content-center">
          <div className="col-sm-10 col-md-7 col-lg-5 col-xl-4">
            <div className="text-center mb-4">
              <h1 className="fw-bold mb-2">
                ServiceFlow
              </h1>

              <p className="text-secondary mb-0">
                Gestão de solicitações
              </p>
            </div>

            <div className="card border-0 shadow-sm">
              <div className="card-body p-4 p-lg-5">
                <div className="text-center mb-4">
                  <div className="mb-3">
                    <LogIn
                      size={40}
                      className="text-primary"
                    />
                  </div>

                  <h2 className="h4 fw-bold mb-2">
                    Entrar
                  </h2>

                  <p className="text-secondary mb-0">
                    Acesse sua conta para continuar.
                  </p>
                </div>

                {error && (
                  <div
                    className="alert alert-danger d-flex align-items-start"
                    role="alert"
                  >
                    <AlertCircle
                      size={20}
                      className="me-2 flex-shrink-0"
                    />

                    <div>{error}</div>
                  </div>
                )}

                <form onSubmit={handleSubmit}>
                  <div className="mb-3">
                    <label
                      htmlFor="username"
                      className="form-label fw-semibold"
                    >
                      Usuário
                    </label>

                    <input
                      id="username"
                      type="text"
                      className="form-control form-control-lg"
                      value={username}
                      onChange={(event) =>
                        setUsername(event.target.value)
                      }
                      placeholder="Digite seu usuário"
                      autoComplete="username"
                      disabled={loading}
                      autoFocus
                    />
                  </div>

                  <div className="mb-4">
                    <label
                      htmlFor="password"
                      className="form-label fw-semibold"
                    >
                      Senha
                    </label>

                    <input
                      id="password"
                      type="password"
                      className="form-control form-control-lg"
                      value={password}
                      onChange={(event) =>
                        setPassword(event.target.value)
                      }
                      placeholder="Digite sua senha"
                      autoComplete="current-password"
                      disabled={loading}
                    />
                  </div>

                  <button
                    type="submit"
                    className="btn btn-primary btn-lg w-100"
                    disabled={loading}
                  >
                    {loading ? (
                      <>
                        <span
                          className="spinner-border spinner-border-sm me-2"
                          aria-hidden="true"
                        />

                        Entrando...
                      </>
                    ) : (
                      <>
                        <LogIn
                          size={18}
                          className="me-2"
                        />

                        Entrar
                      </>
                    )}
                  </button>
                </form>
              </div>
            </div>
          </div>
        </div>
      </main>
    </div>
  )
}

export default Login
