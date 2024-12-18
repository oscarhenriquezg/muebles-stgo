'use client'

import { useState, useEffect, useCallback } from 'react'
import { Sidebar } from "@/components/sidebar"
import { TopBar } from "@/components/top-bar"
import { Button } from "@/components/ui/button"
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "@/components/ui/card"
import { Input } from "@/components/ui/input"
import { Label } from "@/components/ui/label"
import { Alert, AlertDescription } from "@/components/ui/alert"
import { CalendarIcon } from 'lucide-react'
import { format } from "date-fns"
import { cn } from "@/lib/utils"
import { Calendar } from "@/components/ui/calendar"
import { Popover, PopoverContent, PopoverTrigger } from "@/components/ui/popover"
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table"
//import { Textarea } from "@/components/ui/textarea"

const API_BASE_URL = 'http://ms-justificativos:8080/api';

interface Justificativo {
  idJustificativos: number;
  rutEmpleado: string;
  fecha: string;
  motivo: string;
}

export default function Justificativos() {
  const [date, setDate] = useState<Date | undefined>(undefined)
  const [rut, setRut] = useState<string>('')
  const [motivo, setMotivo] = useState<string>('')
  const [submitStatus, setSubmitStatus] = useState<string>('')
  const [attemptedSubmit, setAttemptedSubmit] = useState(false)
  const [justificativos, setJustificativos] = useState<Justificativo[]>([])
  const [isLoading, setIsLoading] = useState(true)
  const [error, setError] = useState<string | null>(null)

  const fetchJustificativos = useCallback(async () => {
    setIsLoading(true)
    setError(null)
    try {
      console.log('Fetching justificativos...')
      const controller = new AbortController()
      const timeoutId = setTimeout(() => controller.abort(), 10000) // 10 segundos de timeout

      const response = await fetch(`${API_BASE_URL}/justificativos`, {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
          'Access-Control-Allow-Origin': '*',
          'Access-Control-Allow-Methods': 'GET, POST, OPTIONS',
          'Access-Control-Allow-Headers': 'Content-Type, Authorization',
        },
        credentials: 'include',
      })

      clearTimeout(timeoutId)

      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`)
      }
      const data = await response.json()
      console.log('Justificativos fetched:', data)
      setJustificativos(data)
    } catch (error) {
      console.error('Error fetching justificativos:', error)
      setError(error instanceof Error ? error.message : 'An unexpected error occurred')
    } finally {
      setIsLoading(false)
    }
  }, [])

  useEffect(() => {
    console.log('Effect running, fetching justificativos...')
    fetchJustificativos()
  }, [fetchJustificativos])

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault()
    setAttemptedSubmit(true)
    if (!date || !rut || !motivo) {
      setSubmitStatus('Error: Por favor, complete todos los campos.')
      return
    }
    setSubmitStatus('Enviando...')
    try {
      const controller = new AbortController()
      const timeoutId = setTimeout(() => controller.abort(), 10000) // 10 segundos de timeout

      const response = await fetch(`${API_BASE_URL}/justificativos`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Access-Control-Allow-Origin': '*',
          'Access-Control-Allow-Methods': 'GET, POST, OPTIONS',
          'Access-Control-Allow-Headers': 'Content-Type, Authorization',
        },
        body: JSON.stringify({
          rutEmpleado: rut,
          fecha: date.toISOString().split('T')[0],
          motivo: motivo,
        }),
        credentials: 'include',
      })

      clearTimeout(timeoutId)

      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`)
      }
      const data = await response.json()
      setSubmitStatus('Justificativo enviado con éxito')
      setRut('')
      setDate(undefined)
      setMotivo('')
      setAttemptedSubmit(false)
      fetchJustificativos()
    } catch (error) {
      console.error('Error:', error)
      setSubmitStatus(error instanceof Error ? error.message : 'An unexpected error occurred')
    }
  }

  console.log('Current state:', { isLoading, error, justificativos })

  return (
      <div className="flex flex-col h-screen">
        <TopBar />
        <div className="flex flex-1 pt-16">
          <Sidebar />
          <main className="flex-1 overflow-y-auto p-6 bg-gray-100">
            <h1 className="text-2xl font-semibold text-gray-800 mb-4">Justificativos</h1>

            <Card className="mb-6">
              <CardHeader>
                <CardTitle>Nuevo Justificativo</CardTitle>
                <CardDescription>Ingrese los detalles del justificativo para el trabajador.</CardDescription>
              </CardHeader>
              <CardContent>
                <form onSubmit={handleSubmit} className="space-y-4">
                  <div className="space-y-2">
                    <Label htmlFor="rut">RUT del Trabajador</Label>
                    <Input
                        id="rut"
                        placeholder="Ingrese el RUT del trabajador"
                        value={rut}
                        onChange={(e) => setRut(e.target.value)}
                        required
                    />
                  </div>

                  <div className="space-y-2">
                    <Label>Fecha</Label>
                    <Popover>
                      <PopoverTrigger asChild>
                        <Button
                            variant={"outline"}
                            className={cn(
                                "w-full justify-start text-left font-normal",
                                !date && "text-muted-foreground"
                            )}
                        >
                          <CalendarIcon className="mr-2 h-4 w-5" />
                          {date ? format(date, "PPP") : <span>Seleccione una fecha</span>}
                        </Button>
                      </PopoverTrigger>
                      <PopoverContent className="w-auto p-0">
                        <Calendar
                            mode="single"
                            selected={date}
                            onSelect={setDate}
                            initialFocus
                            required
                        />
                      </PopoverContent>
                    </Popover>
                    {!date && attemptedSubmit && (
                        <p className="text-sm text-red-500 mt-1">Por favor, seleccione una fecha</p>
                    )}
                  </div>

                  <div className="space-y-2">
                    <Label htmlFor="motivo">Motivo</Label>
                    <Input
                        id="motivo"
                        placeholder="Ingrese el motivo del justificativo"
                        value={motivo}
                        onChange={(e) => setMotivo(e.target.value)}
                        required
                    />
                  </div>

                  <Button type="submit">Enviar Justificativo</Button>
                </form>

                {submitStatus && (
                    <Alert className="mt-4">
                      <AlertDescription>{submitStatus}</AlertDescription>
                    </Alert>
                )}
              </CardContent>
            </Card>

            <Card>
              <CardHeader>
                <CardTitle>Lista de Justificativos</CardTitle>
                <CardDescription>Justificativos registrados en el sistema.</CardDescription>
              </CardHeader>
              <CardContent>
                {isLoading ? (
                    <p>Cargando justificativos...</p>
                ) : error ? (
                    <Alert variant="destructive">
                      <AlertDescription>{error}</AlertDescription>
                    </Alert>
                ) : justificativos.length === 0 ? (
                    <p>No hay justificativos registrados.</p>
                ) : (
                    <Table>
                      <TableHeader>
                        <TableRow>
                          <TableHead>ID</TableHead>
                          <TableHead>RUT Empleado</TableHead>
                          <TableHead>Fecha</TableHead>
                          <TableHead>Motivo</TableHead>
                        </TableRow>
                      </TableHeader>
                      <TableBody>
                        {justificativos.map((justificativo) => (
                            <TableRow key={justificativo.idJustificativos}>
                              <TableCell>{justificativo.idJustificativos}</TableCell>
                              <TableCell>{justificativo.rutEmpleado}</TableCell>
                              <TableCell>{new Date(justificativo.fecha).toLocaleDateString()}</TableCell>
                              <TableCell>{justificativo.motivo}</TableCell>
                            </TableRow>
                        ))}
                      </TableBody>
                    </Table>
                )}
              </CardContent>
            </Card>
          </main>
        </div>
      </div>
  )
}

