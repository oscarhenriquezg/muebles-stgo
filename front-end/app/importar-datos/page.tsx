'use client'

import { useState } from 'react'
import { Sidebar } from "@/components/sidebar"
import { TopBar } from "@/components/top-bar"
import { Button } from "@/components/ui/button"
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table"
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "@/components/ui/card"
import { Alert, AlertDescription, AlertTitle } from "@/components/ui/alert"
import { Upload, AlertCircle } from 'lucide-react'

export default function ImportarDatos() {
  const [data, setData] = useState<any[]>([])
  const [uploadStatus, setUploadStatus] = useState<string>('')

  const handleFileUpload = (event: React.ChangeEvent<HTMLInputElement>) => {
    const file = event.target.files?.[0]
    if (file) {
      setUploadStatus('Subiendo archivo...')
      const reader = new FileReader()
      reader.onload = (e) => {
        const text = e.target?.result
        if (typeof text === 'string') {
          const rows = text.split('\n').map(row => row.split(','))
          setData(rows)
          setUploadStatus('Archivo subido y procesado con éxito.')
        }
      }
      reader.onerror = () => {
        setUploadStatus('Error al procesar el archivo.')
      }
      reader.readAsText(file)
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
                En esta sección podrá importar datos desde un archivo CSV. 
                Asegúrese de que su archivo tenga el formato correcto antes de subirlo.
              </CardDescription>
            </CardHeader>
            <CardContent>
              <p>Pasos para importar datos:</p>
              <ol className="list-decimal list-inside mt-2">
                <li>Prepare su archivo CSV con los datos a importar.</li>
                <li>Haga clic en el botón "Subir Archivo" y seleccione su archivo CSV.</li>
                <li>Espere a que el archivo se procese y los datos se muestren en la tabla.</li>
              </ol>
            </CardContent>
          </Card>

          <div className="mb-4">
            <Button asChild>
              <label className="cursor-pointer">
                <Upload className="mr-2 h-4 w-4" />
                Subir Archivo
                <input type="file" className="hidden" onChange={handleFileUpload} accept=".csv" />
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

          <Card>
            <CardHeader>
              <CardTitle>Datos Importados</CardTitle>
              <CardDescription>
                Los datos de su archivo CSV se mostrarán aquí una vez subidos.
              </CardDescription>
            </CardHeader>
            <CardContent>
              {data.length > 0 ? (
                <Table>
                  <TableHeader>
                    <TableRow>
                      {data[0].map((header: string, index: number) => (
                        <TableHead key={index}>{header}</TableHead>
                      ))}
                    </TableRow>
                  </TableHeader>
                  <TableBody>
                    {data.slice(1).map((row, rowIndex) => (
                      <TableRow key={rowIndex}>
                        {row.map((cell: string, cellIndex: number) => (
                          <TableCell key={cellIndex}>{cell}</TableCell>
                        ))}
                      </TableRow>
                    ))}
                  </TableBody>
                </Table>
              ) : (
                <p className="text-center text-gray-500">No hay datos para mostrar. Por favor, suba un archivo CSV.</p>
              )}
            </CardContent>
          </Card>
        </main>
      </div>
    </div>
  )
}

