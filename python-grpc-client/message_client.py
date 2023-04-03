import grpc
import messages_pb2 as messages_pb2
import service_pb2_grpc as service_grpc


class UnaryClient(object):

    def __init__(self):
        self.host = 'localhost'
        self.server_port = 8080
        self.channel = grpc.insecure_channel(
            '{}:{}'.format(self.host, self.server_port))
        self.stub = service_grpc.MessageServiceStub(self.channel)

    def get_url(self, text):
        message = messages_pb2.MessageRequest(text=text)
        print(f'{message}')
        return self.stub.AddMessage(message)


if __name__ == '__main__':
    client = UnaryClient()
    result = client.get_url(text="Hello Server you there? I'm Python")
    print(f'{result}')
