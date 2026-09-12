import { useState } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import { AlertCircle, CheckCircle2, Plus, ArrowLeft } from 'lucide-react'
import { createServiceRequest } from '../services/api.js'

function NewRequest() {
  const navigate = useNavigate()

  const [title, setTitle] = useState('')
  const [description, setDescription] = useState('')
  const [loading, setLoading] = useState(false)
  const [error, setError] = useState('')

  const [createdRequest, setCreatedRequest] = useState(null)

  async function handleSubmit(event) {
    event.preventDefault()

    setError('')

    if (!title.trim() || !description.trim()) {
      setError('Preencha o título e a descrição da solicitação.')
      return
    }

    try {
      setLoading(true)

      const request = await createServiceRequest({
        title: title.trim(),
        description: description.trim(),
      })

      setCreatedRequest(request)
    } catch (error) {
      console.error('Erro ao criar solicitação:', error)

      if (error.response?.status === 400) {
        setError(
          'Os dados informados são inválidos. Verifique o formulário e tente novamente.',
        )
      } else {
        setError(
          'Não foi possível criar a solicitação. Verifique se a API está disponível e tente novamente.',
        )
      }
    } finally {
      setLoading(false)
    }
  }

  function handleNewRequest() {
    setTitle('')
    setDescription('')
    setError('')
    setCreatedRequest(null)
  }

  if (createdRequest) {
    return (
      <div className="min-vh-100 bg-light">
        <nav className="navbar navbar-dark bg-dark shadow-sm">
          <div className="container">
            <Link
              to="/"
              className="navbar-brand fw-bold text-decoration-none"
            >
              ServiceFlow
            </Link>

            <span className="text-light">
              Gestão de solicitações
            </span>
          </div>
        </nav>

        <main className="container py-5">
          <div className="row justify-content-center">
            <div className="col-lg-7">
              <div className="card border-0 shadow-sm">
                <div className="card-body text-center p-5">
                  <div className="mb-4">
                    <CheckCircle2
                      size={64}
                      strokeWidth={1.5}
                      className="text-success"
                    />
                  </div>

                  <h1 className="fw-bold mb-3">
                    Solicitação criada com sucesso
                  </h1>

                  <p className="text-secondary mb-4">
                    O chamado foi registrado no ServiceFlow e
                    armazenado pela API.
                  </p>

                  <div className="bg-light rounded p-4 mb-4">
                    <small className="text-secondary d-block mb-1">
                      Número da solicitação
                    </small>

                    <span className="fs-2 fw-bold">
                      #{createdRequest.id}
                    </span>

                    <hr />

                    <p className="fw-semibold mb-1">
                      {createdRequest.title}
                    </p>

                    <span className="badge text-bg-warning">
                      {createdRequest.status}
                    </span>
                  </div>

                  <div className="d-flex justify-content-center gap-2 flex-wrap">
                    <Link to="/" className="btn btn-primary">
                      Ver solicitações
                    </Link>

                    <button
                      type="button"
                      className="btn btn-outline-secondary"
                      onClick={handleNewRequest}
                    >
                      <Plus size={18} className="me-1" />
                      Criar outro chamado
                    </button>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </main>
      </div>
    )
  }

  return (
    <div className="min-vh-100 bg-light">
      <nav className="navbar navbar-dark bg-dark shadow-sm">
        <div className="container">
          <Link
            to="/"
            className="navbar-brand fw-bold text-decoration-none"
          >
            ServiceFlow
          </Link>

          <span className="text-light">
            Gestão de solicitações
          </span>
        </div>
      </nav>

      <main className="container py-5">
        <div className="row justify-content-center">
          <div className="col-lg-8">
            <div className="d-flex align-items-center mb-4">
              <Link
                to="/"
                className="btn btn-outline-secondary me-3"
                title="Voltar para o dashboard"
              >
                <ArrowLeft size={18} />
              </Link>

              <div>
                <span className="badge text-bg-primary mb-2">
                  Nova solicitação
                </span>

                <h1 className="fw-bold mb-1">
                  Criar solicitação
                </h1>

                <p className="text-secondary mb-0">
                  Registre uma nova solicitação de serviço.
                </p>
              </div>
            </div>

            {error && (
              <div
                className="alert alert-danger d-flex align-items-start"
                role="alert"
              >
                <AlertCircle size={20} className="me-2 flex-shrink-0" />

                <div>{error}</div>
              </div>
            )}

            <div className="card border-0 shadow-sm">
              <div className="card-body p-4 p-lg-5">
                <form onSubmit={handleSubmit}>
                  <div className="mb-4">
                    <label
                      htmlFor="title"
                      className="form-label fw-semibold"
                    >
                      Título
                    </label>

                    <input
                      id="title"
                      type="text"
                      className="form-control form-control-lg"
                      value={title}
                      onChange={(event) => setTitle(event.target.value)}
                      placeholder="Ex.: Computador sem acesso à rede"
                      disabled={loading}
                      maxLength={100}
                    />

                    <div className="form-text">
                      Informe um título curto que identifique o chamado.
                    </div>
                  </div>

                  <div className="mb-4">
                    <label
                      htmlFor="description"
                      className="form-label fw-semibold"
                    >
                      Descrição
                    </label>

                    <textarea
                      id="description"
                      className="form-control"
                      rows="6"
                      value={description}
                      onChange={(event) =>
                        setDescription(event.target.value)
                      }
                      placeholder="Descreva o problema ou a solicitação."
                      disabled={loading}
                    />

                    <div className="form-text">
                      Explique o problema com informações suficientes
                      para o atendimento.
                    </div>
                  </div>

                  <div className="d-flex justify-content-end gap-2">
                    <Link
                      to="/"
                      className="btn btn-outline-secondary"
                    >
                      Cancelar
                    </Link>

                    <button
                      type="submit"
                      className="btn btn-primary"
                      disabled={loading}
                    >
                      {loading ? (
                        <>
                          <span
                            className="spinner-border spinner-border-sm me-2"
                            aria-hidden="true"
                          />
                          Criando...
                        </>
                      ) : (
                        <>
                          <Plus size={18} className="me-1" />
                          Criar solicitação
                        </>
                      )}
                    </button>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
      </main>
    </div>
  )
}

export default NewRequest
