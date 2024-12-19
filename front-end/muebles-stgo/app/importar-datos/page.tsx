'use client'

import { useState, useEffect } from 'react'
import { Sidebar } from "@/components/sidebar"
import { TopBar } from "@/components/top-bar"
import { Button } from "@/components/ui/button"
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "@/components/ui/card"
import { Alert, AlertDescription, AlertTitle } from "@/components/ui/alert"
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table"
import { Upload, AlertCircle } from 'lucide-react'
import Image from 'next/image'

interface Marca {
  fecha: string;
  hora: string;
  rutEmpleado: string;
}

export default function ImportarDatos() {
  const [uploadStatus, setUploadStatus] = useState<string>('')
  const [isLoading, setIsLoading] = useState<boolean>(false)
  const [marcas, setMarcas] = useState<Marca[]>([])
  const [marcasError, setMarcasError] = useState<string | null>(null)

  const handleFileUpload = async (event: React.ChangeEvent<HTMLInputElement>) => {
    const file = event.target.files?.[0]
    if (file) {
      if (file.name.toUpperCase() !== 'DATA.TXT') {
        setUploadStatus('Error: El archivo debe llamarse DATA.TXT')
        return
      }

      setIsLoading(true)
      setUploadStatus('Subiendo archivo...')

      const formData = new FormData()
      formData.append('file', file)

      try {
        const response = await fetch('http://172.19.94.187:30002/api/marcas/upload', {
          method: 'POST',
          body: formData,
        })

        if (!response.ok) {
          throw new Error(`HTTP error! status: ${response.status}`)
        }

        const result = await response.text()
        setUploadStatus(result)
        fetchMarcas()
      } catch (error) {
        console.error('Error completo:', error)
        if (error instanceof TypeError && error.message === 'Failed to fetch') {
          setUploadStatus('Error: No se pudo conectar con el servidor. Por favor, asegúrese de que el servidor esté en ejecución y sea accesible.')
        } else {
          setUploadStatus(`Error al subir el archivo: ${error instanceof Error ? error.message : 'Error desconocido'}`)
        }
      } finally {
        setIsLoading(false)
      }
    }
  }

  const fetchMarcas = async () => {
    try {
      const response = await fetch('http://172.19.94.187:30002/api/marcas')
      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`)
      }
      const data = await response.json()
      setMarcas(data)
      setMarcasError(null)
    } catch (error) {
      console.error('Error fetching marcas:', error)
      setMarcasError('Error al obtener las marcas. Por favor, intente nuevamente.')
    }
  }

  useEffect(() => {
    if (uploadStatus.includes('éxito')) {
      fetchMarcas()
    }
  }, [uploadStatus])

  return (
      <div className="flex flex-col h-screen">
        <TopBar />
        <div className="flex flex-1 pt-16">
          <Sidebar />
          <main className="flex-1 overflow-y-auto p-6 bg-gray-100">
            <h1 className="text-2xl font-semibold text-gray-800 mb-4">Importar Datos</h1>

            <Card className="mb-6">
              <CardHeader>
                <CardTitle>Instrucciones</CardTitle>
                <CardDescription>
                  En esta sección podrá importar datos desde un archivo de texto (TXT).
                  Asegúrese de que su archivo tenga el formato correcto antes de subirlo.
                </CardDescription>
              </CardHeader>
              <CardContent>
                <p>Pasos para importar datos:</p>
                <ol className="list-decimal list-inside mt-2">
                  <li>Prepare su archivo de texto con el nombre DATA.TXT.</li>
                  <li>Asegúrese de que el archivo tenga el siguiente formato:</li>
                  <Image
                      src="/ejemplo-data-txt.png"
                      alt="Ejemplo de formato DATA.TXT"
                      width={250}
                      height={150}
                      className="my-4 border border-gray-300 rounded"
                  />
                  <li>Haga clic en el botón "Subir DATA.TXT" y seleccione el archivo DATA.TXT.</li>
                  <li>Espere a que el archivo se procese y se muestre el mensaje de confirmación.</li>
                </ol>
              </CardContent>
            </Card>

            <div className="mb-4">
              <Button
                  disabled={isLoading}
                  className="w-full sm:w-auto"
              >
                <label className="cursor-pointer flex items-center justify-center w-full">
                  <Upload className="mr-2 h-5 w-5" />
                  <span>{isLoading ? 'Subiendo...' : 'Subir DATA.TXT'}</span>
                  <input
                      type="file"
                      className="hidden"
                      onChange={handleFileUpload}
                      accept=".txt"
                      disabled={isLoading}
                  />
                </label>
              </Button>
            </div>

            {uploadStatus && (
                <Alert variant={uploadStatus.startsWith('Error') ? 'destructive' : 'default'} className="mb-6">
                  <AlertCircle className="h-4 w-4" />
                  <AlertTitle>{uploadStatus.startsWith('Error') ? 'Error' : 'Estado de la subida'}</AlertTitle>
                  <AlertDescription>{uploadStatus}</AlertDescription>
                </Alert>
            )}

            {marcasError && (
                <Alert variant="destructive" className="mb-6">
                  <AlertCircle className="h-4 w-4" />
                  <AlertTitle>Error</AlertTitle>
                  <AlertDescription>{marcasError}</AlertDescription>
                </Alert>
            )}

            {marcas.length > 0 && (
                <Card>
                  <CardHeader>
                    <CardTitle>Marcas Importadas</CardTitle>
                    <CardDescription>Lista de marcas registradas en el sistema.</CardDescription>
                  </CardHeader>
                  <CardContent>
                    <Table>
                      <TableHeader>
                        <TableRow>
                          <TableHead>Fecha</TableHead>
                          <TableHead>Hora</TableHead>
                          <TableHead>RUT Empleado</TableHead>
                        </TableRow>
                      </TableHeader>
                      <TableBody>
                        {marcas.map((marca, index) => (
                            <TableRow key={index}>
                              <TableCell>{marca.fecha}</TableCell>
                              <TableCell>{marca.hora}</TableCell>
                              <TableCell>{marca.rutEmpleado}</TableCell>
                            </TableRow>
                        ))}
                      </TableBody>
                    </Table>
                  </CardContent>
                </Card>
            )}
          </main>
        </div>
      </div>
  )
}

