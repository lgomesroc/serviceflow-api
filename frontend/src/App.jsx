import { useEffect, useState } from 'react'
import { Link, Routes, Route } from 'react-router-dom'
import {
  AlertCircle,
  ClipboardList,
  Clock3,
  CheckCircle2,
  Plus,
  RefreshCw,
  ChevronLeft,
  ChevronRight,
  XCircle,
} from 'lucide-react'
import NewRequest from './pages/NewRequest.jsx'
import { getServiceRequests } from './services/api.js'

function StatusBadge({ status }) {
  const statusConfig = {
    PENDING: {
      label: 'Pendente',
      className: 'text-bg-warning',
    },
    IN_PROGRESS: {
      label: 'Em andamento',
      className: 'text-bg-primary',
    },
    COMPLETED: {
      label: 'Concluído',
      className: 'text-bg-success',
    },
    CANCELLED: {
      label: 'Cancelado',
      className: 'text-bg-secondary',
    },
  }

  const config = statusConfig[status] || {
    label: status,
    className: 'text-bg-secondary',
  }

  return (
    <span className={`badge ${config.className}`}>
      {config.label}
    </span>
  )
}

function Dashboard() {
  const [requests, setRequests] = useState([])
  const [page, setPage] = useState(0)
  const [totalElements, setTotalElements] = useState(0)
  const [totalPages, setTotalPages] = useState(0)
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState('')

  async function loadRequests(pageNumber = page) {
    try {
      setLoading(true)
      setError('')

      const data = await getServiceRequests(pageNumber, 10)

      setRequests(data.content)
      setTotalElements(data.totalElements)
      setTotalPages(data.totalPages)
      setPage(data.number)
    } catch (error) {
      console.error('Erro ao carregar solicitações:', error)

      setError(
        'Não foi possível carregar as solicitações. Verifique se a API está disponível.',
      )
    } finally {
      setLoading(false)
    }
  }

  useEffect(() => {
    loadRequests(page)
  }, [page])

  const pending = requests.filter(
    (request) => request.status === 'PENDING',
  ).length

  const inProgress = requests.filter(
    (request) => request.status === 'IN_PROGRESS',
  ).length

  const completed = requests.filter(
    (request) => request.status === 'COMPLETED',
  ).length

  const cancelled = requests.filter(
    (request) => request.status === 'CANCELLED',
  ).length

  function handlePreviousPage() {
    if (page > 0) {
      setPage(page - 1)
    }
  }

  function handleNextPage() {
    if (page < totalPages - 1) {
      setPage(page + 1)
    }
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
        <div className="d-flex justify-content-between align-items-start mb-5 flex-wrap gap-3">
          <div>
            <span className="badge text-bg-primary mb-3">
              Central de atendimento
            </span>

            <h1 className="display-5 fw-bold">
              Gestão de solicitações
            </h1>

            <p className="lead text-secondary mt-3 mb-0">
              Acompanhe e gerencie os chamados registrados no ServiceFlow.
            </p>
          </div>

          <div>
            <Link
              to="/solicitacoes/nova"
              className="btn btn-primary btn-lg"
            >
              <Plus size={20} className="me-1" />
              Nova solicitação
            </Link>
          </div>
        </div>

        {error && (
          <div
            className="alert alert-danger d-flex align-items-start"
            role="alert"
          >
            <AlertCircle size={20} className="me-2 flex-shrink-0" />

            <div className="flex-grow-1">
              {error}
            </div>

            <button
              type="button"
              className="btn btn-sm btn-outline-danger"
              onClick={() => loadRequests(page)}
            >
              <RefreshCw size={16} className="me-1" />
              Tentar novamente
            </button>
          </div>
        )}

        <div className="row g-4 mb-4">
          <div className="col-md-6 col-xl">
            <div className="card border-0 shadow-sm h-100">
              <div className="card-body p-4">
                <div className="d-flex justify-content-between align-items-start">
                  <div>
                    <h6 className="text-secondary mb-2">
                      Total
                    </h6>

                    <p className="display-6 fw-bold mb-1">
                      {loading ? '—' : totalElements}
                    </p>

                    <small className="text-secondary">
                      Chamados registrados
                    </small>
                  </div>

                  <ClipboardList className="text-primary" size={28} />
                </div>
              </div>
            </div>
          </div>

          <div className="col-md-6 col-xl">
            <div className="card border-0 shadow-sm h-100">
              <div className="card-body p-4">
                <div className="d-flex justify-content-between align-items-start">
                  <div>
                    <h6 className="text-secondary mb-2">
                      Pendentes
                    </h6>

                    <p className="display-6 fw-bold mb-1">
                      {loading ? '—' : pending}
                    </p>

                    <small className="text-secondary">
                      Nesta página
                    </small>
                  </div>

                  <Clock3 className="text-warning" size={28} />
                </div>
              </div>
            </div>
          </div>

          <div className="col-md-6 col-xl">
            <div className="card border-0 shadow-sm h-100">
              <div className="card-body p-4">
                <div className="d-flex justify-content-between align-items-start">
                  <div>
                    <h6 className="text-secondary mb-2">
                      Em andamento
                    </h6>

                    <p className="display-6 fw-bold mb-1">
                      {loading ? '—' : inProgress}
                    </p>

                    <small className="text-secondary">
                      Nesta página
                    </small>
                  </div>

                  <RefreshCw className="text-primary" size={28} />
                </div>
              </div>
            </div>
          </div>

          <div className="col-md-6 col-xl">
            <div className="card border-0 shadow-sm h-100">
              <div className="card-body p-4">
                <div className="d-flex justify-content-between align-items-start">
                  <div>
                    <h6 className="text-secondary mb-2">
                      Concluídas
                    </h6>

                    <p className="display-6 fw-bold mb-1">
                      {loading ? '—' : completed}
                    </p>

                    <small className="text-secondary">
                      Nesta página
                    </small>
                  </div>

                  <CheckCircle2 className="text-success" size={28} />
                </div>
              </div>
            </div>
          </div>

          <div className="col-md-6 col-xl">
            <div className="card border-0 shadow-sm h-100">
              <div className="card-body p-4">
                <div className="d-flex justify-content-between align-items-start">
                  <div>
                    <h6 className="text-secondary mb-2">
                      Cancelados
                    </h6>

                    <p className="display-6 fw-bold mb-1">
                      {loading ? '—' : cancelled}
                    </p>

                    <small className="text-secondary">
                      Nesta página
                    </small>
                  </div>

                  <XCircle className="text-secondary" size={28} />
                </div>
              </div>
            </div>
          </div>
        </div>

        <div className="card border-0 shadow-sm">
          <div className="card-body p-4">
            <div className="d-flex justify-content-between align-items-center mb-4 flex-wrap gap-3">
              <div>
                <h4 className="fw-bold mb-1">
                  Solicitações recentes
                </h4>

                <p className="text-secondary mb-0">
                  {loading
                    ? 'Carregando solicitações...'
                    : `Exibindo ${requests.length} chamados da página ${
                        page + 1
                      } de ${totalPages}.`}
                </p>
              </div>

              <button
                type="button"
                className="btn btn-outline-secondary"
                onClick={() => loadRequests(page)}
                disabled={loading}
                title="Atualizar lista"
              >
                <RefreshCw size={18} />
              </button>
            </div>

            {loading ? (
              <div className="text-center py-5">
                <div
                  className="spinner-border text-primary"
                  role="status"
                >
                  <span className="visually-hidden">
                    Carregando...
                  </span>
                </div>

                <p className="text-secondary mt-3 mb-0">
                  Carregando solicitações...
                </p>
              </div>
            ) : requests.length === 0 ? (
              <div className="text-center py-5">
                <ClipboardList
                  size={48}
                  className="text-secondary mb-3"
                />

                <h5 className="fw-bold">
                  Nenhuma solicitação encontrada
                </h5>

                <p className="text-secondary">
                  Crie a primeira solicitação para começar.
                </p>

                <Link
                  to="/solicitacoes/nova"
                  className="btn btn-primary"
                >
                  <Plus size={18} className="me-1" />
                  Criar solicitação
                </Link>
              </div>
            ) : (
              <>
                <div className="table-responsive">
                  <table className="table align-middle mb-0">
                    <thead>
                      <tr>
                        <th>ID</th>
                        <th>Título</th>
                        <th>Status</th>
                        <th>Data de criação</th>
                      </tr>
                    </thead>

                    <tbody>
                      {requests.map((request) => (
                        <tr key={request.id}>
                          <td className="fw-semibold">
                            #{request.id}
                          </td>

                          <td>
                            <div className="fw-semibold">
                              {request.title}
                            </div>

                            <small className="text-secondary">
                              {request.description}
                            </small>
                          </td>

                          <td>
                            <StatusBadge status={request.status} />
                          </td>

                          <td className="text-secondary">
                            {request.createdAt
                              ? new Date(
                                  request.createdAt,
                                ).toLocaleString('pt-BR')
                              : '—'}
                          </td>
                        </tr>
                      ))}
                    </tbody>
                  </table>
                </div>

                <div className="d-flex justify-content-between align-items-center mt-4 pt-4 border-top flex-wrap gap-3">
                  <div className="text-secondary">
                    <span className="fw-semibold">
                      {requests.length}
                    </span>{' '}
                    chamados nesta página
                    <span className="mx-2">•</span>
                    <span className="fw-semibold">
                      {totalElements}
                    </span>{' '}
                    chamados no total
                  </div>

                  <div className="d-flex align-items-center gap-2">
                    <button
                      type="button"
                      className="btn btn-outline-secondary"
                      onClick={handlePreviousPage}
                      disabled={page === 0 || loading}
                    >
                      <ChevronLeft size={18} />
                      Anterior
                    </button>

                    <span className="fw-semibold px-2">
                      Página {page + 1} de {totalPages}
                    </span>

                    <button
                      type="button"
                      className="btn btn-outline-secondary"
                      onClick={handleNextPage}
                      disabled={
                        page >= totalPages - 1 || loading
                      }
                    >
                      Próxima
                      <ChevronRight size={18} />
                    </button>
                  </div>
                </div>
              </>
            )}
          </div>
        </div>
      </main>
    </div>
  )
}

function App() {
  return (
    <Routes>
      <Route path="/" element={<Dashboard />} />
      <Route
        path="/solicitacoes/nova"
        element={<NewRequest />}
      />
    </Routes>
  )
}

export default App
