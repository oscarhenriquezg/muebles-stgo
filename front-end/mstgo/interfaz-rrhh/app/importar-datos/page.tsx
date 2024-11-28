'use client'

import { useState } from 'react'
import { Sidebar } from "@/components/sidebar"
import { TopBar } from "@/components/top-bar"
import { Button } from "@/components/ui/button"
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "@/components/ui/card"
import { Alert, AlertDescription, AlertTitle } from "@/components/ui/alert"
import { Upload, AlertCircle } from 'lucide-react'

export default function ImportarDatos() {
  const [uploadStatus, setUploadStatus] = useState<string>('')
  const [isLoading, setIsLoading] = useState<boolean>(false)

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
        const response = await fetch('http://localhost:8080/api/marcas/upload', {
          method: 'POST',
          body: formData,
        })

        if (!response.ok) {
          throw new Error(`HTTP error! status: ${response.status}`)
        }

        const result = await response.text()
        setUploadStatus(result)
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
                <li>Haga clic en el botón "Subir Archivo" y seleccione el archivo DATA.TXT.</li>
                <li>Espere a que el archivo se procese y se muestre el mensaje de confirmación.</li>
              </ol>
              <p className="mt-4 text-sm text-gray-600">
                Nota: Asegúrese de que el servidor backend esté en ejecución en http://localhost:8080 antes de intentar subir el archivo.
              </p>
            </CardContent>
          </Card>

          <div className="mb-4">
            <Button asChild disabled={isLoading}>
              <label className="cursor-pointer">
                <Upload className="mr-2 h-4 w-4" />
                {isLoading ? 'Subiendo...' : 'Subir DATA.TXT'}
                <input type="file" className="hidden" onChange={handleFileUpload} accept=".txt" disabled={isLoading} />
              </label>
            </Button>
          </div>

          {uploadStatus && (
            <Alert className="mb-4">
              <AlertCircle className="h-4 w-4" />
              <AlertTitle>Estado de la subida</AlertTitle>
              <AlertDescription>{uploadStatus}</AlertDescription>
            </Alert>
          )}
        </main>
      </div>
    </div>
  )
}

